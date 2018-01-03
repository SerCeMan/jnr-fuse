package ru.serce.jnrfuse.examples

import jnr.ffi.Platform
import jnr.ffi.Pointer
import ru.serce.jnrfuse.ErrorCodes
import ru.serce.jnrfuse.FuseFillDir
import ru.serce.jnrfuse.FuseStubFS
import ru.serce.jnrfuse.struct.FileStat
import ru.serce.jnrfuse.struct.FuseFileInfo

import java.nio.file.Paths
import java.util.Objects

/**
 * Scala example based on the original Java code by Sergey Tselovalnikov
 * 
 * @author Nicolau Leal Werneck 
 * @see <a href="http://fuse.sourceforge.net/helloworld.html">helloworld</a>
 * @since 01.12.17
 */

sealed trait DirEntity

case class DirFile(contents: Array[Byte]) extends DirEntity

case class Directory(stuff: Map[String, DirEntity]) extends DirEntity {
  def get(path: String): Option[DirEntity] = path.split("/", 3) match {
    case Array("", "") => Some(this)
    case Array("", path_end) => stuff.get(path_end)
    case Array("", path_head, path_tail) => stuff.get(path_head).flatMap(
      _ match {
        case dd: Directory => dd.get("/" + path_tail)
        case _ => None
      }
    )
    case _ => None
  }

  def mapOpt(path: String)(func: PartialFunction[DirEntity,Int]): Int =
    this.get(path) flatMap func.lift getOrElse -ErrorCodes.ENOENT()
}

class HelloFuseFs extends FuseStubFS {

  val HELLO_PATH = "/hello"
  val HELLO_STR = "Hello World!\n"

  val myTree = Directory(
    Map(
      "."-> Directory(Map()),
      ".."-> Directory(Map()),
      HELLO_PATH.drop(1) -> DirFile(HELLO_STR.getBytes())
    )
  )

  override def getattr(path: String, stat: FileStat): Int =
    myTree.mapOpt(path) {
      case Directory(ff) =>
        stat.st_mode.set(FileStat.S_IFDIR | BigInt("755", 8))
        stat.st_nlink.set(2 + ff.size)
        0
      case DirFile(ff) =>
        stat.st_mode.set(FileStat.S_IFREG | BigInt("444", 8))
        stat.st_nlink.set(1)
        stat.st_size.set(ff.length)
        0
    }

  override def readdir(path: String, buf: Pointer, filter: FuseFillDir, offset: Long, fi: FuseFileInfo): Int =
    myTree.mapOpt(path) {
      case Directory(dd) =>
        for (entry <- dd.keys) filter.apply(buf, entry, null, 0)
        0
    }

  override def read(path: String, buf: Pointer, size: Long, offset: Long, fi: FuseFileInfo): Int =
    myTree.mapOpt(path) {
      case DirFile(myData) =>
        val myLength = size.toInt min (0 max (myData.length - offset.toInt))
        buf.put(0L, myData.drop(offset.toInt).take(myLength), 0, myLength)
        myLength
    }
}

object HelloFuse extends App {
  val stub = new ScalaRouterFs()
  val path = "/tmp/mnth"
  stub.mount(Paths.get(path), true, true)
  stub.umount()
}
