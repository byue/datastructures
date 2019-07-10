# Project 1 (Zip) Write-Up #
--------

#### How Was Your Partnership? ####
-   Did both partners do an equal amount of work?  If not, why not?
    If so, what did each person do? What happened?<pre>
**TODO**: 
Yes. Bryan did CircularArrayFIFOQueue & ListFIFOQueue & MinFourHeap and Preston did ArrayStack & MinFourHeap. We worked together on the rest of the project. We debugged our own programs and edited each others's programs. Preston worked on HashTrieMap/SuffixTries and Bryan worked on RandomWorkList/zip testing/formatting the writeup. We both discussed planning in detail before writing the code. We were very flexible with working around our busy schedules to make time for discussing/planning the project. We look forward to working together again on future projects. 
</pre><br>

-----

#### Project Enjoyment ####
-   What was your favorite part of the project?  What was your least
    favorite part of the project?<pre>
**TODO**: 
Bryan's favorite part of the project was comparing our implementations of the data structures to Java's implementations. In particular Bryan enjoyed implementing the circular stack as it was a good refresher on data structures and it was fun to think about what error cases were necessary or not. It was fun to see how close on a performance level (speed and memory usage) we could get to Java's data structure. Bryan's least favorite part was debugging as he spent many hours trying to find where an error was that turned out to be one line of code. However, he realizes debugging is an important part of projects and was glad to have picked up some debugging skills. He used print statements and downloaded a package to help visualize how the data structures were set up. Preston's favorite part of the project was implementing hashtriemaps because it was fun to plan the data structure methods by drawing out all possible cases and later accounting for them in the code. Preston's least favorite part of the project was the SuffixTrieMap since he spent the most time debugging that program. We are trying to figure out why the buffer size is not working properly, and we are thinking that it has to do with the suffix trie program because the buffer size works well with the default jar suffix trie file. 
</pre><br>
    
-   Did you enjoy the project?  Why or why not?<pre>
**TODO**: 
We enjoyed the project because it was nice to see how many different data structures and programs can work in a coordinated manner to produce zip files. It was satisfying to see the end result after hours of debugging and searching for small errors. The part we did not enjoy was that the smallest of errors could mess up the entire program, and that it is very easy for small errors to slip through our test programs we wrote. Thus, we realized the importance of writing several test programs that tested the weaknesses of our implemented structures from many angles. 
</pre><br>

-----

#### WorkLists, Tries, and Zip ####
-   The ADT for a WorkList explicitly forbids access to the middle elements.  However, the FixedSizeFIFOWorkList has a peek(i) method
    which allows you to do exactly that.  Why is this an acceptable addition to the WorkList ADT in this particular case but not in general?  
    In other words, what about fixed size FIFO worklists makes peek(i) make sense?<pre>
**TODO**: 
peek(i) makes sense for fixed size FIFO worklists because we have a well-defined order, a fixed size, and can immediately access the data at index i. The inclusion of peek(i) in the FIFO fixed size worklist interface does not restrict the implementation. The index means the ith element to be removed. A Priority Worklist stores its elements in priority order, but we cannot make immediate assumptions of how middle elements are ordered. If we tried to implement peek(i) for a priority worklist, we would not be able to immediately access the data at index i. Since peek(i) will not be immediate access for Priority Worklist, we do not restrict the interface to having this method and will leave it up to the user to implement in a subclass. For a FIFO worklist, the size is not necesarily fixed, and we do not want to restrict the implementation to an array (they could still implement as a linked list but would be forced include peek(i) which is not constant access, so usage of a linked list is discouraged). If peek(i) was in the interface for FIFO worklist accessing the data at i would not be immediate (for a linked list you have to traverse through each node). Therefore peek(i) is left out of the FIFO worklist interface and left to the user for implementation in a subclass if they choose to. peek(i) is also not appropriate for the LIFO worklist because we do not want to restrict the implementation in the LIFO worklist interface. If we required peek(i) then implementation as a linked list would be discouraged because accessing the data at i would not be immediate (for a linked list you have to loop through each node). Since fixed sized FIFO worklists have already defined characteristics suitable for peek(i), including peek(i) does not restrict implementation. 
</pre><br>
-   As we've described it, a `TrieMap` seems like a general-purpose replacement for `HashMap` or `TreeMap`.  Why might we still want to use one
    of these other data structures instead?<pre>
**TODO**:
TrieMap is not a general-purpose replacement for HashMap or TreeMap because the key type is restricted to characters (alphabet-chars, alphabetic letters, bytes, etc.). TrieMap only works well when the key to be stored can be represented as a sequence. For example, a String object would be suitable for a TrieMap since it's a sequence of characters. Other acceptable types for TrieMap include byte[] and List<E>. Ints, Dictionary<E>, or some other self-defined classes with several fields, cannot be easily represented as sequences, and so HashMap or TreeMap would be better choices for these cases.
<br> In addition, a TrieMap could take up more memory in certain cases where the benefit of shared parts of keys do not outweight the memory the is taken up the having pointers for every node. For example, If every key is very different, we would have many linear chains of nodes branching from the root. We would not save that much space from shared parts of keys, and we would take up a lot of space from having a lot of pointers. In this case, a dictionary would be a better option since less pointers would be used. 
</pre><br>
-   One of the applications of Tries is in solving Word Searches.  A "word search" is an n x m rectangle of letters.  The goal is to find all
    of the possible words (horizontal, vertical, diagonal, etc.).  In Boggle, a similar game, any consecutive chain of letters
    are allowed.  Explain (in very high-level psuedo-code) how you might solve this problem with a TrieSet or a TrieMap.  Make sure to detail
    how a similar solution that uses a HashSet/HashMap instead would be different and why using a Trie might make the solution better.<pre>
**TODO**: 
	For boggle games, we normally have a dictionary which contains all the accepted words. It's better to store the dictionary in a Trie than in HashSet/HashMap, since there will be a lot of words with shared prefixes.To solve the boggle game, we loop through every square in the game, and perform recursive backtracking by starting from one grid and checking the 8 possible squares around it to build the words
	<br> The advantage of using a Trie is that during the recursive backtracking, we can use the findPrefix() method; if it returns false, that means it's guaranteed to have no words under this prefix. When that happens, we can simply treat that as a base case in the recursion and return. This will cut off a lot of unnecessary searches in our backtracking algorithms.  
	For example, "ant" not in the hashmap/hashset cannot guarantee us that "antenna" not in the map/set since each word with shared prefixes is stored separately, so our algorithms cannot stop that branch when "ant" is not found in the dictionary. But with a Trie, if findPrefix("ant") returns false, there is definitely no "antenna" in the dictionary, therefore, we can terminate that branch of backtracking and cut off all the search starting from that branch. 
</pre><br>
-   One of the classes in the main package is called Zip.  This class uses your PriorityQueue to do Huffman coding, your FIFOQueue as a buffer,
    your stack to calculate the keyset of a trie (using recursive backtracking), and your SuffixTrie to do LZ77Compression.  Find some text file
    (a free book from https://www.gutenberg.org/ or even the HTML of some website) and use Zip.java to zip it into a zip file.  Then, use a 
    standard zip utility on your machine (Finder on OS X, zip on Linux, WinZip or the like on Windows) to UNZIP your file.  Check that you got back
    the original.  Congratulations!  Your program correctly implements the same compression algorithm you have been using for years!  Discuss in a
    sentence or two how good the compression was and why you think it was good or bad.<pre> 
**TODO**:
	Our compression correctly preserved all the information in the file exactly the same as the original file. So speaking of the correctness of compression, it performs well.<br>
For the runtime of our algorithm, We tried to compress a 6.3 MB txt file and the runtime of our algorithms is about one second slower than WinRAR performed (BUFFER_LENGTH set as 200).<br>
However, the size of the zip file using our algorithm is 3,871 KB while the one WinRAR generated is only 2,334 KB. Apparently, our program performs poorly on compressing the size of the zip file. We believe that this can be improved by making the BUFFER_LENGTH mentioned in the next question bigger than 200. In the meanwhile, a larger buffer size will slow down our algorithm. This is a design choice between the performance of the algorithm and the optimization of compression size. 
</pre><br>
-   Now that you've played with Zip, we want you to do an **experiment** with Zip.  Notice that there is a constant called `BUFFER_LENGTH` in `Zip.java`.
    Higher values of this constant makes the compression algorithm that Zip uses use more memory and consequently more time.  The "compression ratio"
    of a file is the uncompressed size divided by the compressed size.  Compare time, type of input file, and compression ratio by running
    your code on various inputs.  We would like an in-depth analysis.  You should try at least one "book-like" file, at least one "website-like" file,
    and some other input of your choice.  We expect you to draw meaningful conclusions and possibly have graphs that convince us of your conclusions.
    This single question is worth almost as much as the implementation of `ArrayStack`; so, please take it seriously.  If you spend less than 20 minutes
    on this question, there is no conceivable way that you answered this question in the way we were intending.<pre>
**TODO**: 
We experimented with three file types: "book-like" text file, a "website-like" html file, and a "picture-like" jpg file. They were all standardized to 37 kb to make comparisons more achievable. We chose Buffer Size as the independent variable and treated runtime and compression ratio as dependent variables for each of the 3 file types. For Buffer size values we chose 1, 100, incrementing by 100 to 1000, 1500, and 2000. Performance was noticeably slower after 1000 for the buffer size. According to the CSV Data file, the runtimes in milliseconds are comparable between the text and html file. However, the jpg runtime starts off longer than the other 2 but becomes less in the long run. The txt and html file involve processing strings, so their runtimes are expected to be similar, but jpg zipping involves byte units. JPG starts off with worse runtime because picture files are more saturated with content, and possibly the byte units are more difficult to compress than character units because there are less common bytes to compress. It doesn't make sense why JPG performs better at higher buffer sizes, and the increase in performance might be due to background noise, and other environmental factors out of our control. When comparing compression ratios, HTML has a higher (better) compression ratio compared to the text file. This can be explained by HTML files having a lot of repetitive strings since tags are used, so the output file will be able to be compressed more compared to the text file, where repetition of strings is less common. The compression ratio of the jpg file is lower than the other two files and stays lower, changing less. This shows that JPEG files are not as easily compressible as text based files. With JPEG files the data is not as repetitive, so the compression algorithm is not as effective for JPEG files. <br> <br> Comparing Buffer size vs Runtime graphs, the shape of the graphs appear to be polynomial. This shape may be due to the fact that we are running out of memory and we have to keep accessing lower level caches to store the data. When we keep accessing lower level caches we incur increasing miss rate penalties and so the runtimes keep increasing. The shape of these graphs do not reflect the algorithms used, but rather the limitations of our hardware. <br> <br> Comparing Buffer Size vs Compression ratio graphs, the compression ratio increases at a decreasing rate since we have less and less increases in compression capability when the buffer size increases. HTML appears to have more gains in compression at lower buffer sizes compared to text files and jpeg files. With a repetitive structure, HTML files take advantage of the compression algorithm more at lower buffer sizes, with diminishing returns as buffer size increases. The JPG compression ratio graph appears not to fit any patterns but we have to keep in mind that the scaling on the y-axis is small, so the jump on the graph is actually very small, and the graph is essentially flat. The compression ratio for JPEG does not change very much, so the jump after the first 3 data points is probably due to background noise, with the change magnified due to scaling. <br> <br> From our analysis we conclude that JPEG files have worse compression than text based files, possibly due to bytes from JPEG being harder to compress as they contain more information. HTML files benefit the most from compression as their repetitive structure and the usage of tries allows for a better use of the compressing algorithm. However, all three types eventually experience performance issues once the buffer size reached 1000, and for large increases in runtime, we do not get a lot more compression. While we tried to optimize performance with efficient data structures, performance is largely dependent on the environment and hardware, and may be affected by background noise. We conclude that file types with a lot of repeated content have higher compression ratios. 

[HTML/JPG/Text Zip Data](https://gitlab.cs.washington.edu/cse332-17wi/p1-nidoqueen/blob/master/src/zipstats/zipstats.csv)

<h3> Text Buffer Size vs Compression Ratio </h3>
<img src="https://i.imgsafe.org/e82f8b6b0b.jpg" width="550" height="300" />

<h3> Text Buffer Size vs Runtime </h3>
<img src="https://i.imgsafe.org/d8a6c69fed.jpg" width="550" height="300" />

<h3> HTML Buffer Size vs Compression Ratio </h3>
<img src="https://i.imgsafe.org/e82f59d1cc.jpg" width="550" height="300" />

<h3> HTML Buffer Size vs Runtime </h3>
<img src="https://i.imgsafe.org/d8a684eadd.jpg" width="550" height="300" />

<h3> JPG Buffer Size vs Compression Ratio </h3>
<img src="https://i.imgsafe.org/e82f714e1b.jpg" width="550" height="300" />

<h3> JPG Buffer Size vs Runtime </h3>
<img src="https://i.imgsafe.org/d8a69e4789.jpg" width="550" height="300" />

</pre><br> 

#### Above and Beyond ####
-   Did you do any Above and Beyond?  Describe exactly what you
    implemented.<pre>
**TODO**:
Preston worked on suffixTrie and Bryan worked on RandomList. In suffixTrie Preston worked on debugging a buffer size problem and we are still working on it. The zip program still compresses properly but buffer size has no affect on the speed or ratio of the compression. With the jar file compression works as predicted, so the bug is probably in the suffixtrie program, specifically in handling the leaves. Bryan implemented a RandomWorkList and optimized the first 4 worklists to shrink the arrays to optimize memory usage. He shrunk the array to 1/2 size when the size was at 1/4 capacity. 1/4 capacity was chosen instead of 1/2 capacity so we can avoid constant doubling/halving sizes when we add/remove elements at 1/2 capacity. For RandomWorkList Bryan implemented the specified methods and had to think about how peek() should work in concert with next() since peeking before next() should return the same value as when you call next(). Bryan also used a set field to keep track of indices in the buffer that did not have data to take care of cases where you have gaps in the array after removing elements. 
</pre><br>
