# theresa
Blazingly intelligent and dynamic dependency injection framework built on google guice inspired by netflix governator providing lifecycle management and other fancy stuff we miss in guice.

# Build Status
|             	| Build Status                                                                                                                                              	| Test Code Coverage                                                                                                                                               	|
|-------------	|-----------------------------------------------------------------------------------------------------------------------------------------------------------	|------------------------------------------------------------------------------------------------------------------------------------------------------------------	|
| Master      	| [![Build Status](https://travis-ci.org/FelixKlauke/theresa.svg?branch=master)](https://travis-ci.org/FelixKlauke/theresa) 	| [![codecov](https://codecov.io/gh/FelixKlauke/theresa/branch/master/graph/badge.svg)](https://codecov.io/gh/FelixKlauke/theresa) 	|
| Development 	| [![Build Status](https://travis-ci.org/FelixKlauke/theresa.svg?branch=dev)](https://travis-ci.org/FelixKlauke/theresa)    	| [![codecov](https://codecov.io/gh/FelixKlauke/theresa/branch/dev/graph/badge.svg)](https://codecov.io/gh/FelixKlauke/theresa)    	|

# Goals
Some time ago I discovered [Netflix Governator](https://github.com/Netflix/governator) and I really loved the idea of
having life cycle management support alongside with dependency injection. Guice was my favourite dependency injection
framework and some time Ago Jordan Zimmerman (mailto:jzimmerman@netflix.com) built a library on top of guice bringing
some nice features like configuration mapping and life cycle management. The goal of this project is to maintain
this idea and functionality in a clean and minimal rewrite.