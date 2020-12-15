import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet
import java.util.Stack
import kotlin.math.max


object Solution {
    @JvmStatic
    fun main(args: Array<String>) {
        val binarySearch = binarySearchRight(intArrayOf(0, 1, 2, 3, 4, 7, 13, 13, 13, 13, 16, 19), 13)
        println("$binarySearch")
    }

    /**
     * 二分查找，数组必须有序
     */
    fun binarySearch(num: IntArray, target: Int): Int {
        var lo = 0
        var hi = num.size - 1
        while (lo < hi) {
            val mid = (lo + hi) / 2
            if (target < num[mid]) {
                hi = mid - 1
            } else if (target > num[mid]) {
                lo = mid + 1
            } else {
                return mid
            }
        }
        return if (num[lo]==target) lo else -1
    }

    /**
     * 二分搜索 左边侧值，则右边下标移动时不减一
     */
    fun binarySearchLeft(num: IntArray, target: Int): Int {
        var lo = 0
        var hi = num.size - 1
        while (lo < hi) {
            val mid = (lo + hi) / 2
            if (target < num[mid]) {
                hi = mid
            } else if (target > num[mid]) {
                lo = mid + 1
            } else {
                hi = mid
            }
        }
        return if (num[lo] == target) lo else -1
    }

    /**
     * 二分搜索 右边侧值，则左边下标移动时不加一
     */
    fun binarySearchRight(num: IntArray, target: Int): Int {
        var lo = 0
        var hi = num.size - 1
        while (lo < hi) {
            val mid = (lo + hi) / 2
            if (target < num[mid]) {
                hi = mid - 1
            } else if (target > num[mid]) {
                lo = mid
            } else {
                lo = mid
            }
        }
        return if (num[hi] == target) hi else -1
    }

    /**
     * 接雨水
     */
    fun trap(height: IntArray): Int {
        var result = 0
        val stack = Stack<Int>()
        for (index in height.indices) {
            while (!stack.isEmpty() && height[index] >= height[stack.peek()]) {
                val pop = stack.pop()
                while (!stack.isEmpty() && height[pop] == height[stack.peek()]) {
                    stack.pop()
                }
                if (stack.isNotEmpty()) {
                    result += (Math.min(height[index], height[stack.peek()]) - height[pop]) * (index - stack.peek() - 1)
                }
            }
            stack.add(index)
        }
        return result
    }

    /**
     * 第 N 个泰波那契数 - 最常用递归，此题用遍历+缓存
     * https://leetcode-cn.com/problems/n-th-tribonacci-number/
     */
    fun tribonacci(n: Int): Int {
        if (n < 3) {
            return if (n == 0) 0 else 1
        }
        var x = 0
        var y = 0
        var z = 1
        var temp = 0
        for (i in 2..n) {
            temp = x + y + z
            x = y
            y = z
            z = temp
        }
        return temp
    }

    /**
     * 括号生成
     * https://leetcode-cn.com/problems/generate-parentheses/
     */
    fun generateParenthesis(n: Int): List<String> {
        val list = mutableListOf<String>()
        helper(list, n, 0, 0, "")
        return list
    }

    fun helper(list: MutableList<String>, max: Int, left: Int, right: Int, result: String) {
        if (left == max && right == max) {
            list.add(result)
            return
        }
        if (left < max) {
            helper(list, max, left + 1, right, "$result(")
        }
        if (right < left) {
            helper(list, max, left, right + 1, "$result)")
        }
    }

    /**
     * 摆动序列
     * https://leetcode-cn.com/problems/wiggle-subsequence/
     */
    fun wiggleMaxLength(nums: IntArray): Int {
        var isPositive = -1
        var temp = if (nums.isEmpty()) 0 else 1

        for (index in nums.indices) {
            if (index == 0) {
                continue
            }
            val poor = nums[index] - nums[index - 1]
            if (poor == 0) {
                continue
            }
            val isPositiveResult = if (poor > 0) 1 else 0
            if (isPositiveResult == isPositive) {
                continue
            }
            isPositive = isPositiveResult
            temp++
        }
        return temp
    }

    /**
     * 有效的括号
     * https://leetcode-cn.com/problems/valid-parentheses/
     */
    fun isValid(s: String): Boolean {
        val chars = s.toCharArray()
        if (chars.size % 2 != 0) {
            return false
        }
        val template: HashMap<Char, Char> = hashMapOf(Pair(')', '('), Pair(']', '['), Pair('}', '{'))
        val list = LinkedList<Char>()
        for (c in chars) {
            if (template.containsKey(c)) {
                if (list.peek() != template[c]) {
                    return false
                } else {
                    list.pop()
                }
            } else {
                list.push(c)
            }
        }
        return list.isEmpty()
    }

    /**
     * 无重复字符的最长子串
     * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/submissions/
     */
    fun lengthOfLongestSubstring(s: String): Int {
        var result = 0
        val tempSet = HashSet<Char>()
        val chars = s.toCharArray()
        for (i in chars.indices) {
            val c = chars[i]
            tempSet.clear()
            tempSet.add(c)
            label@ for (j in i + 1 until chars.size) {
                if (tempSet.contains(chars[j])) {
                    break@label
                }
                tempSet.add(chars[j])
            }
            result = Math.max(result, tempSet.size)
        }
        return result
    }
}