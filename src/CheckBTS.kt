object CheckBTS {

    @JvmStatic
    fun main(args: Array<String>) {

    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    /**
     * 验证二叉搜索树 中序排列 -> 升序排列
     */
    var pre: TreeNode? = null
    private fun isValidBST(root: TreeNode?): Boolean {
        root ?: return true
        if (!isValidBST(root.left)) {
            return false
        }
        if (null != pre && pre!!.`val` >= root.`val`) {
            return false
        }
        pre = root
        return isValidBST(root.right)
    }

    /**
     * 前序排列
     */
    private fun preorder(tree: TreeNode?, list: MutableList<Int>): List<Int> {
        tree ?: return list
        list.add(tree.`val`)
        preorder(tree.left, list)
        preorder(tree.right, list)
        return list
    }

    /**
     * 中序排列
     */
    private fun middleOrder(tree: TreeNode?, list: MutableList<Int>): List<Int> {
        tree ?: return list
        middleOrder(tree.left, list)
        list.add(tree.`val`)
        middleOrder(tree.right, list)
        return list
    }

    /**
     * 后序排列
     */
    private fun postOrder(tree: TreeNode?, list: MutableList<Int>): List<Int> {
        tree ?: return list
        postOrder(tree.left, list)
        postOrder(tree.right, list)
        list.add(tree.`val`)
        return list
    }
}