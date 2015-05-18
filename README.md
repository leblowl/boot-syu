# boot-syu
This is a tool for updating the dependency list in a project's build.boot file. Inspired by ```pacman -Syu```, boot-syu simply replaces dependencies with the latest versions available. 

[ancient-clj](https://github.com/xsc/ancient-clj) is used for the core artifact retrieval.

## Usage
1) In your build.boot file, include boot-syu as a project dependency and require it:
   ```clojure
   (require '[boot-syu.task :refer :all])
   ```
2) Now you can call ```boot syu [-q | -s | -a]``` from the command line in the project directory or use the syu task directly from within your build.boot file.

The syu task takes 3 optional arguments, described in the task definition:
```clojure
  [s snapshots bool "include SNAPSHOT versions"
   q qualified bool "include alpha, beta, etc... versions"
   a all       bool "include snapshots and qualified versions"]
```
