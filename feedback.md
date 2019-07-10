# Project 1 (Zip) Feedback #
## CSE 332 Winter 2017 ##

**Team:** Bryan Yue (byue) and Preston Jiang (prestonj) <br />
**Graded By:** Michael Lee (mlee42@cs.washington.edu)
<br>

## Unit Tests ##

**ArrayStack**  `(9/9)`
> ✓ Passed *testHasWork* <br>
> ✓ Passed *testHasWorkAfterAdd* <br>
> ✓ Passed *testHasWorkAfterAddRemove* <br>
> ✓ Passed *testPeekHasException* <br>
> ✓ Passed *testNextHasException* <br>
> ✓ Passed *testClear* <br>
> ✓ Passed *checkStructure* <br>
> ✓ Passed *stressTest* <br>
> ✓ Passed *fuzzyStressTest* <br>

**CircularArrayFIFOQueue**  `(14/14)`
> ✓ Passed *testHasWork* <br>
> ✓ Passed *testHasWorkAfterAdd* <br>
> ✓ Passed *testHasWorkAfterAddRemove* <br>
> ✓ Passed *testPeekHasException* <br>
> ✓ Passed *testNextHasException* <br>
> ✓ Passed *testClear* <br>
> ✓ Passed *checkStructure* <br>
> ✓ Passed *testPeekAndUpdateEmpty* <br>
> ✓ Passed *testPeekAndUpdateOutOfBounds* <br>
> ✓ Passed *testUpdatingOutOfBounds* <br>
> ✓ Passed *testUpdate* <br>
> ✓ Passed *testCycle* <br>
> ✓ Passed *stressTest* <br>
> ✓ Passed *fuzzyStressTest* <br>

**ListFIFOQueue**  `(9/9)`
> ✓ Passed *testHasWork* <br>
> ✓ Passed *testHasWorkAfterAdd* <br>
> ✓ Passed *testHasWorkAfterAddRemove* <br>
> ✓ Passed *testPeekHasException* <br>
> ✓ Passed *testNextHasException* <br>
> ✓ Passed *testClear* <br>
> ✓ Passed *checkStructure* <br>
> ✓ Passed *stressTest* <br>
> ✓ Passed *fuzzyStressTest* <br>

**MinFourHeap**  `(18/18)`
> ✓ Passed *testHasWork* <br>
> ✓ Passed *testHasWorkAfterAdd* <br>
> ✓ Passed *testHasWorkAfterAddRemove* <br>
> ✓ Passed *testPeekHasException* <br>
> ✓ Passed *testNextHasException* <br>
> ✓ Passed *testClear* <br>
> ✓ Passed *checkStructure* <br>
> ✓ Passed *testHeapWith5Items* <br>
> ✓ Passed *testHugeHeap* <br>
> ✓ Passed *testOrderingDoesNotMatter* <br>
> ✓ Passed *testWithCustomComparable* <br>
> ✓ Passed *testStructureInorderInput* <br>
> ✓ Passed *testStructureReverseOrderInput* <br>
> ✓ Passed *testStructureInterleavedInput* <br>
> ✓ Passed *testStructureRandomInput* <br>
> ✓ Passed *testStructureWithDups* <br>
> ✓ Passed *stressTest* <br>
> ✓ Passed *fuzzyStressTest* <br>

**HashTrieMap**  `(18/19)`
> ✓ Passed *testBasic* <br>
> ✓ Passed *testBasicDelete* <br>
> ✓ Passed *testFindPrefixes* <br>
> ✓ Passed *testFindNonexistentDoesNotCrash* <br>
> ✓ Passed *testFindingNullEntriesCausesError* <br>
> ✓ Passed *testInsertReplacesOldValue* <br>
> ✓ Passed *testInsertingNullEntriesCausesError* <br>
> ✓ Passed *testDeleteAll* <br>
> ✓ Passed *testDeleteNothing* <br>
> ✓ Passed *testDeleteAndInsertSingleChars* <br>
> ✓ Passed *testDeleteWorksWhenTrieHasNoBranches* <br>
> ✓ Passed *testDeletingAtRoot* <br>
> ✓ Passed *testDeletingEmptyString* <br>
> ✓ Passed *testDeletingNullEntriesCausesError* <br>
> ✓ Passed *testClear* <br>
> ✓ Passed *checkUnderlyingStructure* <br>
> ✓ Passed *stressTest* <br>
> ✓ Passed *testSize* <br>
> `✘ Failed` *testSizeWorksWithMissing* <br>

**SuffixTrie**  `(10/10)`
> ✓ Passed *testExampleFromSpec* <br>
> ✓ Passed *testExampleFromSpecUsingSmallBufferSize* <br>
> ✓ Passed *testExampleFromSpecUsingDifferentBufferSizes* <br>
> ✓ Passed *testAllUnique* <br>
> ✓ Passed *testAllSame* <br>
> ✓ Passed *testParagraph* <br>
> ✓ Passed *testRepetitive* <br>
> ✓ Passed *testDna* <br>
> ✓ Passed *testFakePaper* <br>
> ✓ Passed *testCourseWebsite* <br>

**SuffixTrieStudent**  `(1/0)`
> `✘ Failed` *testExampleFromSpec* <br>
> ✓ Passed *testExampleFromSpecUsingSmallBufferSize* <br>
> `✘ Failed` *testExampleFromSpecUsingDifferentBufferSizes* <br>
> ✓ Passed *testAllUnique* <br>
> `✘ Failed` *testAllSame* <br>
> `✘ Failed` *testParagraph* <br>
> `✘ Failed` *testRepetitive* <br>
> `✘ Failed` *testDna* <br>
> `✘ Failed` *testFakePaper* <br>
> `✘ Failed` *testCourseWebsite* <br>

## Miscellaneous ##

`(-0/0)` <br />


When working on your writeup, please make sure your doc looks nice on Gitlab!
(If you view WriteUp.md on Gitlab, it'll be rendered as an HTML doc).

In particular, be sure to wrap your lines.

If you'd like to learn more about the file format Gitlab understands, google
"markdown".

--------

## Write-Up ##

**Partnership**
`(1/1)`

Glad to hear it!

**How was the project?**
`(1/1)`

Yeah, debugging can be pretty tricky, but I'm glad to hear you had fun!


### WorkLists, Tries, and Zip ###

**peek(i)**
`(2/2)`

You have a lot of discussion about why peek(i) might not be appropriate for particular
subclasses of WorkList, but ultimately, none of that discussion explains why peek(i)
isn't appropriate for a _WorkList_ in general. After all, what if somebody creates a
new WorkList subclass?

Furthermore, the fact that peek(i) will need to be O(n) or worse for certain implementations
isn't really relevant since the question is asking about an ADT, not particular
implementations. It could be the case that the implementor decides an O(n) peek(i) is an
acceptable tradeoff for their particular use case.



**TrieMap vs. (HashMap and TreeMap)**
`(2/2)`

**Applications of TrieMap**
`(3/3)`

Since we only if care if a word exists or not, a TrieSet would be a better choice than a TrieMap.

**Running Zip**
`(1/1)`

**Zip Experiment**
`(6/6)`

We actually process all files as bytes, regardless of whether they're text files or not.

As it turns out, JPEG files are already pre-compressed. You should think about what
happens when you try compressing an already-compressed file.

While cache misses may be a factor in the increased runtime, it's unlikely to
really be a significant factor in the grand scheme of things. Remember, we focus
heavily in this class on asymptotics. You should try finding a more straightforward
explanation.

Other then that, your experiments look pretty reasonable. As fair warning though,
we plan on grading experiments much more harshly in the future. It might be a good
idea to start doing things like conducting multiple trials, collecting more data
points, etc. on P2 and P3.

### Above and Beyond ###

**Above and Beyond**
`(EX: 2)`
Does not properly implement reservoir sampling.
In future projects, please put your above and beyond code in a separate package
if you want it to be considered for extra credit.
