# answers from clojure org test:

## Functions: (answers at the end of file)

1. Define a function greet that takes no arguments and prints "Hello". Replace the with the implementation: (defn greet [] )
  
2. Redefine greet using def, first with the fn special form and then with the #() reader macro.
```
;; using fn
(def greet __)

;; using #()
(def greet __)
```
  
3. Define a function greeting which:
  
Given no arguments, returns "Hello, World!"
Given one argument x, returns "Hello, x!"
Given two arguments x and y, returns "x, y!"
```
;; Hint use the str function to concatenate strings
(doc str)

(defn greeting ___)

;; For testing
(assert (= "Hello, World!" (greeting)))
(assert (= "Hello, Clojure!" (greeting "Clojure")))
(assert (= "Good morning, Clojure!" (greeting "Good morning" "Clojure")))
```
  
4. Define a function do-nothing which takes a single argument x and returns it, unchanged.
```
(defn do-nothing [x] ___)
```
In Clojure, this is the identity function. By itself, identity is not very useful, but it is sometimes necessary when working with higher-order functions.
```
(source identity)
```
  
5. Define a function always-thing which takes any number of arguments, ignores all of them, and returns the number 100.
```
(defn always-thing [__] ___)
```
  
6. Define a function make-thingy which takes a single argument x. It should return another function, which takes any number of arguments and always returns x.
```
(defn make-thingy [x] ___)

;; Tests
(let [n (rand-int Integer/MAX_VALUE)
      f (make-thingy n)]
  (assert (= n (f)))
  (assert (= n (f 123)))
  (assert (= n (apply f 123 (range)))))
```
In Clojure, this is the constantly function.
```
(source constantly)
```
  
7. Define a function triplicate which takes another function and calls it three times, without any arguments.
```
(defn triplicate [f] ___)
```
  
8. Define a function opposite which takes a single argument f. It should return another function which takes any number of arguments, applies f on them, and then calls not on the result. The not function in Clojure does logical negation.
```
(defn opposite [f]
  (fn [& args] ___))
```
In Clojure, this is the complement function.
```
(defn complement
  "Takes a fn f and returns a fn that takes the same arguments as f,
  has the same effects, if any, and returns the opposite truth value."
  [f]
  (fn
    ([] (not (f)))
    ([x] (not (f x)))
    ([x y] (not (f x y)))
    ([x y & zs] (not (apply f x y zs)))))
```
  
9. Define a function triplicate2 which takes another function and any number of arguments, then calls that function three times on those arguments. Re-use the function you defined in the earlier triplicate exercise.
```
(defn triplicate2 [f & args]
  (triplicate ___))
```
  
10. Using the java.lang.Math class (Math/pow, Math/cos, Math/sin, Math/PI), demonstrate the following mathematical facts:
  
The cosine of pi is -1
For some x, sin(x)^2 + cos(x)^2 = 1
  
11. Define a function that takes an HTTP URL as a string, fetches that URL from the web, and returns the content as a string.
  
Hint: Using the java.net.URL class and its openStream method. Then use the Clojure slurp function to get the content as a string.
```
(defn http-get [url]
  ___)

(assert (.contains (http-get "https://www.w3.org") "html"))
```
In fact, the Clojure slurp function interprets its argument as a URL first before trying it as a file name. Write a simplified http-get:
```
(defn http-get [url]
  ___)
```
  
12. Define a function one-less-arg that takes two arguments:
  
f, a function
x, a value
and returns another function which calls f on x plus any additional arguments.
```
(defn one-less-arg [f x]
  (fn [& args] ___))
```
In Clojure, the partial function is a more general version of this.
  
13. Define a function two-fns which takes two functions as arguments, f and g. It returns another function which takes one argument, calls g on it, then calls f on the result, and returns that.
  
That is, your function returns the composition of f and g.
```
(defn two-fns [f g]
  ___)
```


## my answers

### 1:
```
(defn greet [] 
    (print "Hello"))
```
  
### 2:
```
(def greet 
    (fn [] (print "Hello")))

(def greet 
    #(print "Hello"))
```
  
### 3:
```
(defn greeting 
    ([] (greeting "Hello" "World"))
    ([thing] (greeting "Hello" thing))
    ([greet, thing] (str greet, ", ", thing, "!"))
)

;; For testing
(assert (= "Hello, World!" (greeting)))
(assert (= "Hello, Clojure!" (greeting "Clojure")))
(assert (= "Good morning, Clojure!" (greeting "Good morning" "Clojure")))
```
  
### 4:
```
(defn do-nothing [x] x)
```
  
### 5:
```
(defn always-thing [& arg] 100)
```
  
### 6:
```
(defn make-thingy [x] 
    (fn [& args] x))

;; Tests
(let [n (rand-int Integer/MAX_VALUE)
      f (make-thingy n)]
  (assert (= n (f)))
  (assert (= n (f 123)))
  (assert (= n (apply f 123 (range)))))
```
  
### 7:
```
(defn triplicate [f] 
    (f) (f) (f))

;; test:
(triplicate greet)
```

### 8:
```
(defn opposite [f] 
    (fn [& args] (not (apply f args))))

;; test:
(defn even [x] (println (= 0 (rem x 2))))
(def odd (opposite even))
(odd 1)
```

### 9:
```
(defn triplicate2 [f & args] 
    (triplicate (fn [] (apply f args))))

;; test:
(triplicate2 even 1)
```

### 10:
```
;; cosine of pi
(print (Math/cos Math/PI))

;; For some x, sin(x)^2 + cos(x)^2 = 1
(def testNumber 5)
(
    (fn [x] (+ 
        (Math/pow (Math/sin x) 2) 
        (Math/pow (Math/cos x) 2)
    ))
    testNumber
)
;; more legible
(defn powOfTwo [x] (Math/pow x 2))
(
    (fn [x] (let [
            sin (Math/sin x)
            cos (Math/cos x)
        ])
        (+ (powOfTwo sin) (powOfTwo cos))
    )
    testNumber
)
```
#### correct answer
```
(+  (Math/pow (Math/sin 0.2) 2)
    (Math/pow (Math/cos 0.2) 2))
```

### 11:
```
(defn http-get [url] 
    (
        let [urlClass (java.net.URL. url)]
        (
            slurp (.openStream urlClass)
        )
    )
)

(defn http-get [url]
    (slurp url))

;; test
(assert (.contains (http-get "https://google.com/") "html"))
```
#### correct answer
```
(defn http-get [url]
    (slurp
        (.openStream
            (java.net.URL. url))))

(defn http-get [url]
    (slurp url))
```

### 12:
```
(defn one-less-arg [f x]
    (fn [& args] (apply f x args)))
```

### 13:
```
(defn two-fns [f g] 
    (fn [x] (f (g x))))
```