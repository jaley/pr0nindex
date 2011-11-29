# pr0nindex

This is a crude web demonstration of the pr0n index, which is the fraction of
results returned by a Google search that only appear with SafeSearch Off. It
can be used as a way of telling whether an input phrase is likely to be offensive.
It's also hours of hilarious fun!

## Usage

If you just want to play, go see this app running on [Google App Engine](http://pr0nindex.appspot.com/). If
on the other hand you want to fork the repo and hack with it, see build instructions below.

## Build

The project is written in Clojure and is set up to run on the Google App Engine. You can do
whatever you like, but here are the steps to run the dev server on your machine.

* Get the Google App Engine SDK from [here](http://code.google.com/appengine/downloads.html).
* Grab Leiningen, the build too we're using from [here](http://github.com/technomancy/leiningen)
* From the project root, run the following to pull down the dependencies.

    $ lein deps

* Compile the Clojure code with:

    $ lein compile

* You can now start the dev server for testing with:

    $ /path/to/gae-sdk/bin/dev_appserver.sh war

Consult the [Google App Engine Documentation](http://code.google.com/appengine/docs/java/overview.html) if you have any issues.

## Testing the App

When you're done with the above build instructions, the app will be running at http://localhost:8080/ - but it won't work. 
You need to edit `src/pr0nindex/key.clj` and add a valid Google API key. You can sign up for one [here](http://code.google.com/apis/loader/signup.html).

There are also some unit tests for the `pr0n-index` function itself in `test/pr0nindex/pr0n.clj`, which you can run like this:

    $ lein test


## License

Copyright (C) 2011 TouchType Ltd.

Distributed under the Eclipse Public License, the same as Clojure.
