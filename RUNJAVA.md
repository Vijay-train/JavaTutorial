Run helper (`run-java.sh`)
=========================

This repository includes a small helper script `run-java.sh` to compile and run Java classes quickly during development.

Usage examples (from project root):

```
./run-java.sh Grap
./run-java.sh Grap.java
```

You can also call the convenience alias `runjava` (added to `~/.bashrc` by the assistant):

```
runjava Grap
```

Notes:
- Compiles into `./out` and runs the requested class (supports package declarations).
- Use `./run-java.sh --help` for more details.
