# answers from clojure org test:

## Syntax:

1. Using the REPL, compute the sum of 7654 and 1234.
2. Rewrite the following algebraic expression as a Clojure expression: ( 7 + 3 * 4 + 5 ) / 10.
3. Using REPL documentation functions, find the documentation for the rem and mod functions. Compare the results of the provided expressions based on the documentation.
4. Using find-doc, find the function that prints the stack trace of the most recent REPL exception.


## Answers

### 1:
```
(+ 7654 1234)
```

### 2:
```
(/ (+ 7 (* 3 4) 5) 10)
```

### 3:
```
(find-doc "rem")
(doc rem)
clojure.core/rem
([num div])
  remainder of dividing numerator by denominator.
```
```
(find-doc "mod")
(doc mod)
clojure.core/mod
([num div])
  Modulus of num and div. Truncates toward negative infinity.
```
both are division operation related function and both returns the division remainder  
rem is the % "mod" that i know  
but mod i didn't understand yet
  
### 4:
```
(find-doc "stack trace")

clojure.repl/pst
([] [e-or-depth] [e depth])
  Prints a stack trace of the exception, to the depth requested. If none supplied, uses the root cause of the
  most recent repl exception (*e), and a depth of 12.
```


