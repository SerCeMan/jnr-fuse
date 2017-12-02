Installation
==

## Linux

[`libfuse`](https://github.com/libfuse/libfuse) needs to be installed.

#### Ubuntu
```bash
sudo apt-get install libfuse-dev
``` 

## MacOS

[`osxfuse`](https://osxfuse.github.io) needs to be installed.

```bash
brew cask install osxfuse
```

## Windows

[`winfsp`](https://github.com/billziss-gh/winfsp) needs to be installed.
```batch
choco install winfsp
```

#### Troubleshooting

* If you see the `service java has failed to start` error or corrupted file names/content, setting
the explicit file encoding `-Dfile.encoding=UTF-8` might help.