import java.util.*
import kotlin.collections.HashMap

object Sum {

    @JvmStatic
    fun main(args: Array<String>) {
    }

    /**
     * 最大公约数
     */
    fun maximumCommonDivisor(q: Int, p: Int): Int {
        if (p == 0) {
            return q
        }
        val r = q % p
        return maximumCommonDivisor(p, r)
    }

    /**
     * 两数之和（不重复）
     */
    fun twoSum(nums: IntArray, target: Int): IntArray {
//        for (i in 0 until nums.size) {
        for (i in nums.indices) {
            for (j in i + 1 until nums.size) {
                if (nums[i] + nums[j] == target) {
                    return intArrayOf(i, j)
                }
            }
        }
        return intArrayOf(0)
    }

    fun twoSum2(nums: IntArray, target: Int): IntArray {
        val map = HashMap<Int, Int>()
        for (i in nums.indices) {
            val result = target - nums[i]
            if (map.containsKey(result)) {
                return intArrayOf(map[result]!!, i)
            }
            map[nums[i]] = i
        }
        return intArrayOf(0)
    }

    /**
     * 柠檬水找零
     * 【模拟算法】
     */
    fun lemonadeChange(bills: IntArray): Boolean {
        var fiveCount = 0
        var tenCount = 0
        for (bill in bills) {
            when (bill) {
                5 -> {
                    fiveCount++
                }
                10 -> {
                    if (fiveCount == 0) {
                        return false
                    }
                    fiveCount--
                    tenCount++
                }
                20 -> {
                    if (fiveCount > 0 && tenCount > 0) {
                        fiveCount--
                        tenCount--
                    } else if (fiveCount >= 3) {
                        fiveCount -= 3
                    } else {
                        return false
                    }
                }
            }
        }
        return true
    }
}