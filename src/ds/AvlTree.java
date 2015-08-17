package ds;

import com.sun.javafx.tools.packager.Log;
import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

import ds.Node;

public class AvlTree {

    private boolean mTaller;
    private Node mRoot;

    public static void main(String[] args) {
        AvlTree tree = new AvlTree();
        tree.createAvlTree();
        //tree.createSortTree();
//        tree.test();
    }


    public void test() {
        int key1 = 3;
        int key2 = 1;
        if (key2 < key1) {
            key2 = key2 ^ key1;
            key1 = key2 ^ key1;
            key2 = key2 ^ key1;
        }
        System.out.println("key1: " + key1 + " key2: " + key2);
    }


    public void createAvlTree() {
        mRoot = null;
        Node root = null;
        int[] data = {3, 2, 1, 4, 5, 6, 7, 10, 9, 8};
        int size = data.length;
        for (int index = 0; index < size; index++) {
            root = insertAvl(root, data[index]);
        }
        mRoot = root;
//        preOrderTravl(mRoot);
//
//        System.out.println("height is : " + getTreeHeight(mRoot));
//        System.out.println("blance is : " + checkBlacne(mRoot));
//        layerTravl(mRoot);
//        preOrderTravlWithoutRes(mRoot);
//        InOrder2(mRoot);
//        PostOrder2(mRoot);
//        testPostOrder(mRoot);
//        postOrderWithOneStack(mRoot);
//        int count = getCount(mRoot);
//        System.out.println("count is " + count);
        int value = getPublicParent(mRoot, 6, 8);
//        System.out.println("parent is "+value);
//
//        value = getPublicParent(mRoot, 6, 5);
//        System.out.println("parent is "+value);
//
//        value = getPublicParent(mRoot, 1, 10);
//        System.out.println("parent is "+value);
//
//        System.out.println("---------------------------------- ");

//        value = getPublicParent2(mRoot, 6, 8, null);
//
//        value = getPublicParent2(mRoot, 6, 5, null);

        value = getPublicParent2(mRoot, 1, 10, null);


        System.out.println("parent is : " + query(new Node(1), new Node(10), mRoot));


    }

    private boolean isTreeSame(Node treeone, Node treeSec) {
        if (treeone.mData == treeSec.mData) {
            return isTreeSame(treeone.mLeftChild, treeSec.mLeftChild) && isTreeSame(treeone.mRightChild, treeSec.mRightChild);
        } else {
            return false;
        }
    }


    private int getTreeHeight(Node root) {
        if (null != root) {
            int leftHeight = getTreeHeight(root.mLeftChild);
            int rightHeight = getTreeHeight(root.mRightChild);
            int height = leftHeight > rightHeight ? leftHeight : rightHeight;
            height += 1;
            return height;
        } else {
            return 0;
        }
    }

    private boolean checkBlacne(Node root) {
        if (null != root) {
            int leftHeight = getTreeHeight(root.mLeftChild);
            int rightHeight = getTreeHeight(root.mRightChild);
            if (Math.abs(leftHeight - rightHeight) > 1) {
                return false;
            } else {
                return checkBlacne(root.mLeftChild) && checkBlacne(root.mRightChild);
            }
        } else {
            return true;
        }
    }


    public void createSortTree() {
        mRoot = null;
        int[] data = {3, 2, 1, 4, 5, 6, 7, 10, 9, 8};
        int size = data.length;
        for (int index = 0; index < size; index++) {
            mRoot = addSortNode(mRoot, data[index]);
        }
        addSortNode(mRoot, 11);
        addSortNode(mRoot, 12);
        addSortNode(mRoot, 13);
        preOrderTravl(mRoot);
        System.out.println("height is : " + getTreeHeight(mRoot));
        System.out.println("blance is : " + checkBlacne(mRoot));
    }

    private Node addSortNode(Node root, int e) {
        if (root == null) {
            root = new Node(e);
            System.out.println(" mRoot: " + mRoot + " root: " + root);
            return root;
        }
        Node node = root;
        Node parent = root;
        while (node != null) {
            parent = node;
            if (e < node.mData) {
                node = node.mLeftChild;
            } else if (e > node.mData) {
                node = node.mRightChild;
            } else {
                return root;
            }
        }
        if (null != parent) {
            if (e < parent.mData) {
                parent.setLeftChild(new Node(e));
            } else if (e > parent.mData) {
                parent.setRightChild(new Node(e));
            }
        }
        return root;

    }


    private boolean mShorter;

    public Node deletelNodeWithOneChild(Node node, Node child) {
        Node parent = node.mParent;
        if (null != parent) {
            int loc = parent.getLoc(node);
            switch (loc) {
                case Node.LEFT_CHILD:
                    parent.setLeftChild(child);
                    node.free();
                    mShorter = true;
                    if (null == child) {
                        return parent;
                    }
                    return child;
                case Node.RIGHT_CHILD:
                    parent.setRightChild(child);
                    node.free();
                    mShorter = true;
                    if (null == child) {
                        return parent;
                    }
                    return parent;
            }
        } else {
            //delete root node


        }
        return child;
    }

    private Node findMax(Node node) {
        Node lChild = node.mLeftChild;
        Node preNode = lChild.mRightChild;
        if (null == preNode) {
            preNode = lChild;
        } else {
            while (preNode.mRightChild != null) {
                preNode = preNode.mRightChild;
            }
        }
        return preNode;
    }

    public Node deleteNode(int e, Node node) {
        if (e == node.mData) {
            if (node.mLeftChild == null) {
                Node newNode = deletelNodeWithOneChild(node, node.mRightChild);
                System.out.println("newCode: " + newNode.mData);
                return newNode;
            } else if (node.mRightChild == null) {
                return deletelNodeWithOneChild(node, node.mLeftChild);
            } else {
                Node lChild = node.mLeftChild;
                Node preNode = lChild.mRightChild;
                if (null == preNode) {
                    preNode = lChild;
                } else {
                    while (preNode.mRightChild != null) {
                        preNode = preNode.mRightChild;
                    }
                }
                System.out.println("preCode=====" + preNode.toString());
                int data = preNode.mData;
                preNode.mData = node.mData;
                node.mData = data;
                Node root = deleteNode(e, node.mLeftChild);
                System.out.println("deleteNode=====" + root.toString() + " node: " + node.toString());
                switch (node.mBH) {
                    case 1:
                        node.mBH = 0;
                        mShorter = false;
                        break;
                    case 0:
                        node.mBH = -1;
                        if (node.mLeftChild != null || node.mRightChild != null) {
                            mShorter = false;
                        } else {
                            mShorter = true;
                        }
                        break;
                    case -1:
                        node.mBH = 0;
                        mShorter = false;
                        root = rightBalance(node);
                        break;
                }
                return root;
            }
        } else if (e < node.mData) {
            Node root = node;
            deleteNode(e, node.mLeftChild);
            switch (node.mBH) {
                case 1:
                    node.mBH = 0;
                    mShorter = false;
                    break;
                case 0:
                    node.mBH = -1;
                    mShorter = true;
                    break;
                case -1:
                    node.mBH = 0;
                    mShorter = false;
                    root = rightBalance(node);
                    break;
            }
            return root;
        } else if (e > node.mData) {
            System.out.println("delete" + node.toString());
            Node root = node;
            deleteNode(e, node.mRightChild);
            switch (node.mBH) {
                case 1:
                    node.mBH = 0;
                    mShorter = false;
                    root = leftBalance(node);
                case 0:
                    node.mBH = 1;
                    mShorter = true;
                    break;
                case -1:
                    node.mBH = 0;
                    mShorter = false;
                    break;
            }
            System.out.println("###### " + root.mData);
            return root;
        }
        return null;
    }


    private Node insertAvl(Node root, int e) {
        if (null == root) {
            root = new Node(e);
            return root;
        }
        if (e < root.mData) {
            if (root.mLeftChild == null) {
                root.setLeftChild(new Node(e, root));
                mTaller = true;
            } else {
                insertAvl(root.mLeftChild, e);
            }
            if (mTaller) {
                switch (root.mBH) {
                    case 1:
                        mTaller = false;
                        root = leftBalance(root);
                        break;
                    case 0:
                        root.mBH += 1;
                        mTaller = true;
                        break;
                    case -1:
                        root.mBH = 0;
                        mTaller = false;
                        break;
                }
            }
        } else if (e > root.mData) {
            if (root.mRightChild == null) {
                root.setRightChild(new Node(e, root));
                mTaller = true;
            } else {
                insertAvl(root.mRightChild, e);
            }
            if (mTaller) {
                switch (root.mBH) {
                    case 1:
                        root.mBH = 0;
                        mTaller = false;
                        break;
                    case 0:
                        root.mBH -= 1;
                        mTaller = true;
                        break;
                    case -1:
                        mTaller = false;
                        root = rightBalance(root);
                }
            }
        }
        return root;
    }

    private Node leftBalance(Node node) {
        Node l, lr;
        l = node.mLeftChild;
        switch (l.mBH) {
            case 1:
                node.mBH = l.mBH = 0;
                return rotateRight(node);
            case -1:
                lr = l.mRightChild;
                switch (lr.mBH) {
                    case 1:
                        node.mBH = -1;
                        l.mBH = 0;
                        break;
                    case 0:
                        node.mBH = l.mBH = 0;
                        break;
                    case -1:
                        node.mBH = 0;
                        l.mBH = 1;
                        break;
                }
                lr.mBH = 0;
                rotateLeft(node.mLeftChild);
                return rotateRight(node);
        }
        return node;

    }

    private Node rightBalance(Node node) {
//		System.out.println("rightBalance: " + node.toString());
        Node r, rl;
        r = node.mRightChild;
        switch (r.mBH) {
            case -1:
                node.mBH = r.mBH = 0;
                return rotateLeft(node);
            case 1:
                rl = r.mLeftChild;
                switch (rl.mBH) {
                    case 1:
                        node.mBH = 0;
                        r.mBH = -1;
                        break;
                    case 0:
                        node.mBH = r.mBH = 0;
                        break;
                    case -1:
                        node.mBH = 1;
                        r.mBH = 0;
                        break;
                }
                rl.mBH = 0;
                rotateRight(node.mRightChild);
                return rotateLeft(node);

        }
        return node;
    }

    private Node rotateLeft(Node node) {
        Node parent = node.mParent;
        Node rightChild = node.mRightChild;
        node.setRightChild(rightChild.mLeftChild);
        rightChild.setLeftChild(node);
        if (null != parent) {
            boolean isLeft = false;
            if (parent.mLeftChild == node) {
                isLeft = true;
            } else if (parent.mRightChild == node) {
                isLeft = false;
            }

            if (isLeft) {
                parent.setLeftChild(rightChild);
            } else {
                parent.setRightChild(rightChild);
            }
        } else {
            rightChild.mParent = null;
        }
        return rightChild;
    }

    private Node rotateRight(Node node) {
        Node parent = node.mParent;
        boolean isLeft = false;
        if (null != parent) {
            if (parent.mLeftChild == node) {
                isLeft = true;
            } else if (parent.mRightChild == node) {
                isLeft = false;
            }
        }
        Node leftChild = node.mLeftChild;
        node.setLeftChild(leftChild.mRightChild);
        leftChild.setRightChild(node);
        if (null != parent) {
            if (isLeft) {
                parent.setLeftChild(leftChild);
            } else {
                parent.setRightChild(leftChild);
            }
        } else {
            leftChild.mParent = null;
        }
        return leftChild;
    }

    private void preOrderTravl(Node root) {
        if (root == null) {
            return;
        }
        System.out.println("pre: " + root.toString());
        preOrderTravl(root.mLeftChild);
        preOrderTravl(root.mRightChild);
    }

    private void inOrderTravl(Node root) {
        if (root == null) {
            return;
        }
        inOrderTravl(root.mLeftChild);
        System.out.println("in: " + root.toString());
        inOrderTravl(root.mRightChild);
    }

    private void layerTravl(Node root) {
        LinkedList<Node> list = new LinkedList<Node>();
        list.add(root);
        while (!list.isEmpty()) {
            Node node = list.remove();
            System.out.println(" " + node.mData);
            if (null != node.mLeftChild) {
                list.add(node.mLeftChild);
            }
            if (null != node.mRightChild) {

                list.add(node.mRightChild);
            }
        }
    }

    private void preOrderTravlWithoutRes(Node root) {
        Stack<Node> stack = new Stack<Node>();
        stack.push(mRoot);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            System.out.println(" " + node.mData);
            if (null != node.mRightChild) {
                stack.push(node.mRightChild);
            }
            if (null != node.mLeftChild) {
                stack.push(node.mLeftChild);
            }

        }
    }

    // 中序遍历非递归
    public static void InOrder2(Node t) {
        Stack<Node> s = new Stack<Node>();
        while (t != null || !s.empty()) {
            while (t != null) {
                s.push(t);
                t = t.mLeftChild;
            }
            if (!s.empty()) {
                t = s.pop();
                System.out.print(t.mData);
                t = t.mRightChild;
            }
        }
    }

    // 后序遍历非递归
    public static void PostOrder2(Node t) {
        Stack<Node> s = new Stack<Node>();
        Stack<Integer> s2 = new Stack<Integer>();
        Integer i = new Integer(1);
        while (t != null || !s.empty()) {
            while (t != null) {
                s.push(t);
                s2.push(new Integer(0));
                t = t.mLeftChild;
            }

            while (!s.empty() && s2.peek().equals(i)) {//考虑一下左子树的最后一个节点没有左节点，但是有右结点的情况
                s2.pop();
                int data = s.pop().mData;
                System.out.println("=====" + data);
            }

            if (!s.empty()) {
                s2.pop();
                s2.push(new Integer(1));
                t = s.peek();
                t = t.mRightChild;
            }
        }
    }

    public void postOrderWithOneStack(Node node) {
        Node pre = null;
        Stack<Node> stack = new Stack<>();
        while (null != node || !stack.isEmpty()) {

            while (node != null) {
                stack.push(node);
                node = node.mLeftChild;
            }

            if (!stack.isEmpty()) {
                Node temp = stack.peek().mRightChild;
                if (null == temp || temp == pre) {
                    node = stack.pop();
                    pre = node;
                    System.out.println("--" + node.mData);
                    node = null;
                } else {
                    node = temp;
                }
            }
        }
    }

    int mCount;

    private void getNodeCount(Node root) {
        if (null != root) {
            return;
        }
        mCount++;
        getNodeCount(root.mLeftChild);
        getNodeCount(root.mRightChild);
    }

    private int getCount(Node root) {
        if (null == root) {
            return 0;
        }
        return getCount(root.mLeftChild) + getCount(root.mRightChild) + 1;
    }

    private void testPostOrder(Node node) {
        Stack<Node> nodeS = new Stack<>();
        Stack<Integer> flagS = new Stack<>();
        Integer flag = new Integer(1);
        while (null != node || !nodeS.isEmpty()) {
            while (null != node) {
                nodeS.push(node);
                flagS.push(new Integer(0));
                node = node.mLeftChild;
            }
            while (!nodeS.isEmpty() && flagS.peek().equals(flag)) {
                flagS.pop();
                System.out.println("---" + nodeS.pop().mData);
            }
            if (!nodeS.isEmpty()) {
                flagS.pop();
                flagS.push(new Integer(1));
                node = nodeS.peek();
                node = node.mRightChild;
            }
        }
    }

    public int getPublicParent2(Node root, int key1, int key2, Node parent) {
        int left = 0;
        int right = 0;

        if (null != root.mLeftChild) {
            parent = root;
            left = getPublicParent2(root.mLeftChild, key1, key2, parent);
        }
        if (null != root.mRightChild) {
            parent = root;
            right = getPublicParent2(root.mRightChild, key1, key2, parent);
        }

        int mid = 0;
        if (root.mData == key1 || root.mData == key2) {
            mid = 1;
        }
        if ((left + right + mid) == 2) {
            if (mid == 1) {
                System.out.println("parent is : " + parent.mData);
            } else {
                System.out.println("parent is : " + root.mData);
            }
        }

        return left | right | mid;
    }


    public int getPublicParent(Node root, int key1, int key2) {
        Node node = root;
        ArrayList<Node> leftList = new ArrayList<Node>();

        while (node != null) {
            if (key1 < node.mData) {
                leftList.add(node);
                node = node.mLeftChild;
            } else if (key1 > node.mData) {
                leftList.add(node);
                node = node.mRightChild;
            } else {
                leftList.add(node);
                break;
            }
        }

        node = root;
        ArrayList<Node> rightList = new ArrayList<>();

        while (node != null) {
            if (key2 < node.mData) {
                rightList.add(node);
                node = node.mLeftChild;
            } else if (key2 > node.mData) {
                rightList.add(node);
                node = node.mRightChild;
            } else {
                rightList.add(node);
                break;
            }
        }
        int parent = 0;
        int size = leftList.size() > rightList.size() ? rightList.size() : leftList.size();
        for (int index = 0; index < size; index++) {
            if (leftList.get(index).mData != rightList.get(index).mData) {
                break;
            } else {
                parent = leftList.get(index).mData;
            }
        }
        return parent;
    }

    public int query(Node t1, Node t2, Node t) {
        int left = t1.mData;
        int right = t2.mData;
        Node parent = null;
        if (left > right) {
            int temp = left;
            left = right;
            right = temp;
        }
        while (true) {
            if (t.mData < left) {
                parent = t;
                t = t.mRightChild;
            } else if (t.mData > right) {
                parent = t;
                t = t.mLeftChild;
            } else if (t.mData == left || t.mData == right) {
                return parent.mData;
            } else {
                return t.mData;
            }
        }
    }

}
