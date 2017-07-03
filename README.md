# Spam Filtering

This homework assignment was part of a Data Mining course offered at FGCU, in Fort Myers, FL. We were given about 2 weeks to complete the assignment and test that the functionality. Our confidence of classification accuracy must also be within a pre-determined confidence interval of 95% +/- 3%.

## Requirements
- Given a 2 directories, each with 100 text files - use both Classification as well as Clustering techniques learned in class to create a filtering system that will calssify the text files as either SPAM or HAM
- Calculate the percent of accuracy of your classification system. Given that each file in the training set is identified as *SP_* for spam. 

## Solution
- While reading from the files, create 2 Map data types of all of the words in each file set and assign the Map with the word and their number of occurances in all or each of the files. We then take that information from the training data and use the Maps we created to identify words in test emails.
- Cosine similarity was used to identify how "similar" one word is from another.
- We created a means to further filter the results my removing known garbage words that may contain multiple special characters, overly repeated characters, or even foul language, etc.
