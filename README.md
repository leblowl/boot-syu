# boot-update-deps
This is a tool for updating the dependency list in a project's build.boot file. It will replace jar entries with the latest versions available, including alpha, beta, SNAPSHOT, etc.

I plan to use xsc/version-clj for version comparison.
At the moment only clojars jars will be updated. 
