package Implement;

import java.util.*;

/**
 * Given an array of words and a length L, format the text such that each line
 * has exactly L characters and is fully (left and right) justified. You should
 * pack your words in a greedy approach; that is, pack as many words as you can
 * in each line. Pad extra spaces ' ' when necessary so that each line has
 * exactly L characters. Extra spaces between words should be distributed as
 * evenly as possible. If the number of spaces on a line do not divide evenly
 * between words, the empty slots on the left will be assigned more spaces than
 * the slots on the right. For the last line of text, it should be left
 * justified and no extra space is inserted between words. For example, words:
 * ["This", "is", "an", "example", "of", "text", "justification."] L: 16. Return
 * the formatted lines as:
 */
//"This    is    an",
// "example  of text",
// "justification.  "
/*  Note: Each word is guaranteed not to exceed L in length.
 */
public class TextJustification {

    public ArrayList<String> fullJustify(String[] words, int L) {
        ArrayList<String> results = new ArrayList<String>();

        int wordsLen = 0;
        int head = 0;
        for (int i = 0; i < words.length; ++i) {
            if (head == i) {
                if (i == words.length - 1) {
                    StringBuilder lineBuilder = new StringBuilder();
                    lineBuilder.append(words[i]);
                    int remain = L - lineBuilder.length();
                    for (int k = 0; k < remain; ++k) {
                        lineBuilder.append(" ");
                    }
                    results.add(lineBuilder.toString());
                } else {
                    wordsLen += words[i].length();
                }
            } else {
                if (wordsLen + words[i].length() + 1 > L) {
                    StringBuilder lineBuilder = new StringBuilder();
                    int intervalNum = i - head - 1;
                    if (intervalNum == 0) {
                        // corner case  
                        lineBuilder.append(words[i - 1]);
                        int remain = L - lineBuilder.length();
                        for (int k = 0; k < remain; ++k) {
                            lineBuilder.append(" ");
                        }
                        results.add(lineBuilder.toString());
                    } else {
                        int totalSpaceNum = L - (wordsLen - intervalNum);
                        int redundantSpaceNum = totalSpaceNum % intervalNum;
                        int spaceNumPerInterval = totalSpaceNum / intervalNum;
                        for (int j = head; j < i - 1; ++j) {
                            lineBuilder.append(words[j]);
                            for (int k = 0; k < spaceNumPerInterval; ++k) {
                                lineBuilder.append(" ");
                            }
                            if (j - head < redundantSpaceNum) {
                                lineBuilder.append(" ");
                            }
                        }
                        lineBuilder.append(words[i - 1]);
                        results.add(lineBuilder.toString());
                    }
                    // new line  
                    head = i;
                    wordsLen = 0;
                    --i;
                } else if (wordsLen + words[i].length() + 1 == L) {
                    StringBuilder lineBuilder = new StringBuilder();
                    for (int j = head; j <= i; ++j) {
                        lineBuilder.append(words[j]).append(" ");
                    }
                    results.add(lineBuilder.substring(0,
                            lineBuilder.length() - 1));

                    // new line  
                    head = i + 1;
                    wordsLen = 0;
                } else {
                    if (i == words.length - 1) {
                        StringBuilder lineBuilder = new StringBuilder();
                        for (int j = head; j <= i; ++j) {
                            lineBuilder.append(words[j]).append(" ");
                        }
                        int remain = L - lineBuilder.length();
                        for (int k = 0; k < remain; ++k) {
                            lineBuilder.append(" ");
                        }
                        results.add(lineBuilder.toString());
                    } else {
                        wordsLen += words[i].length() + 1;
                    }
                }
            }
        }
        return results;
    }

    //Has a bug
    public ArrayList<String> fullJustify2(String[] words, int L) {
        ArrayList<String> result = new ArrayList<String>();
        if (words == null || words.length < 1 || L < 1) {
            String line = "";
            for (int i = 0; i < L; i++) {
                line += " ";
            }
            result.add(line);
            return result;
        }

        for (int i = 0; i < words.length; i++) {
            int letterCount = 0;
            int count = 0;
            int start = i;
            while (i < words.length - 1 && count + words[i].length() <= L) {
                letterCount = letterCount + words[i].length();
                count = count + 1 + words[i].length(); //每個字都需要一個space, 除了最後一個字
                i++;
            }

            int wordNum = i - start;
            int spaceAdd;
            int[] spaceNums;
            if (wordNum == 1) {
                String line = words[start];
                for (int j = line.length(); j < L; j++) {
                    line += " ";
                }
                result.add(line);
            } else if (i == words.length - 1) {   //最后一行的情况  
                String line = words[start];
                int spacenum = L - count;
                for (int j = start + 1; j <= i; j++) {
                    line += " " + words[j];
                }
                for (int s = 0; s < spacenum; s++) {
                    line += " ";
                }
                result.add(line);
            } else {
                int spaceNum = L - letterCount;
                int everyslot = spaceNum / (wordNum - 1);
                int addone = spaceNum % (wordNum - 1);
                String line = words[start];
                for (int k = start + 1; k <= i; k++) {
                    for (int j = 0; j < everyslot; j++) {
                        line += " ";
                    }
                    if (addone > 0) {
                        line += " ";
                        addone--;
                    }
                    line += words[k];
                }
                result.add(line);
            }
        }
        return result;
    }


    /**
     * 還是有bug...
     * @param words
     * @param L
     * @return
     */
    public ArrayList<String> fullJustify3(String[] words, int L) {
        ArrayList<String> result = new ArrayList<String>();

        int wordsIndex = 0;

        while (wordsIndex < words.length) {
            int strLength = 0;
            int leftIndex = wordsIndex;

            while (wordsIndex < words.length && (strLength + words[wordsIndex].length() <= L)) {
                strLength = strLength + words[wordsIndex].length();
                strLength = strLength == L ? strLength : strLength + 1;
                wordsIndex++;
            }

            int wordNum = wordsIndex - leftIndex;

            StringBuilder line = new StringBuilder();
            if (wordNum == 1) {
                line.append(words[leftIndex]);
                for (int i = 0; i < L - words[leftIndex].length(); i++) {
                    line.append(" ");
                }
                result.add(line.toString());
                continue;
            }

            int letterLength = 0;

            for (int i = leftIndex; i < wordsIndex; i++) {
                letterLength += words[i].length();
            }

            int spaceTotal = L - letterLength;
            int spaceSlot = wordsIndex - leftIndex - 1;
            int aveSpace = spaceTotal / spaceSlot;
            int[] spaceSlotNum = new int[spaceSlot];

            for (int i = spaceSlot - 1; i >= 0; i--) {
                spaceSlotNum[i] = aveSpace;
                spaceTotal -= aveSpace;
            }

            if (spaceTotal > 0)
                spaceSlotNum[0] = spaceSlotNum[0] + spaceTotal;

            for (int i = leftIndex; i < wordsIndex; i++) {
                line.append(words[i]);
                if (i - leftIndex < spaceSlot) {
                    for (int j = 0; j < spaceSlotNum[i]; j++) {
                        line.append(" ");
                    }
                }
            }

            result.add(line.toString());
        }


        return result;
    }


    public static void main(String[] args) {
        String[] test = {"This", "is", "an", "example", "of", "text", "justification."};
        TextJustification t = new TextJustification();
        System.out.println(t.fullJustify3(test, 16));
    }
}
