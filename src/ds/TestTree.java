package ds;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by grass on 7/24/15.
 */
public class TestTree {

    private Node mRoot;
//    private int[] DATA = {3, 2, 1, 4, 5, 6, 7, 10, 9, 8};

    private int[] DATA = {3, 2, 1, 4, 5};
    public static void main(String[] args) {
        TestTree tree = new TestTree();
        Node root = tree.createSortTree();
//        tree.inOrder(root);
//        tree.inOrderNoTraversal(root);
//        tree.layerOrder(root);
//        tree.posOrderNoTraversalTwoStack(root);
//        System.out.println("=================");
//        tree.posOrderNoTraversalOneStack(root);
//        tree.layerOrderWithTravsersal(root,7);
//        System.out.println("depth " + tree.getDepth(root));
//        System.out.println("maxdepth " + tree.getMaxDepth(root));
//        System.out.println("blance " + tree.isBlance(root));
//        System.out.println("blance " + tree.isBlance2(root));
//        System.out.println("get second layer count " + tree.getKLayerCount(root, 2));
//        System.out.println("get second layer count " + tree.getKLayerCount(root, 3));
        tree.preOrder(root);
        System.out.println("=================");
        root = tree.mirror(root);
        tree.preOrder(root);
    }

    public Node createSortTree() {
        for (int data : DATA) {
            addNode(data);
        }
        return mRoot;
    }

    public void addNode(int data) {
        if (mRoot == null) {
            mRoot = new Node(data);
            return;
        }
        Node parent = null;
        Node node = mRoot;
        while (node != null) {
            parent = node;
            if (data < node.mData) {
                node = node.mLeftChild;
            } else if (data > node.mData) {
                node = node.mRightChild;
            } else {
                throw new RuntimeException("can not have duplicate item");
            }
        }
        if (data < parent.mData) {
            parent.setLeftChild(new Node(data));
        } else if (data > parent.mData) {
            parent.setRightChild(new Node(data));
        }
    }

    public void preOrder(Node root) {
        if (null == root) {
            return;
        }
        System.out.println("inOrder--" + root.mData);
        preOrder(root.mLeftChild);
        preOrder(root.mRightChild);
    }

    public void inOrder(Node root) {
        if (null == root) {
            return;
        }
        inOrder(root.mLeftChild);
        System.out.println("inOrder--" + root.mData);
        inOrder(root.mRightChild);
    }

    public void inOrderNoTraversal(Node root) {
        Stack<Node> stack = new Stack<Node>();
        Node node = root;
        while (null != node || !stack.isEmpty()) {
            while ((node != null)) {
                stack.push(node);
                node = node.mLeftChild;
            }
            if (!stack.isEmpty()) {
                Node popNode = stack.pop();
                System.out.println("---" + popNode.mData);
                node = popNode.mRightChild;
            }
        }
    }

    public void layerOrder(Node root) {
        LinkedList<Node> list = new LinkedList<>();
        list.add(root);
        while (!list.isEmpty()) {
            Node node = list.remove();
            System.out.println("---" + node.mData);
            if (node.mLeftChild != null) {
                list.add(node.mLeftChild);
            }
            if (node.mRightChild != null) {
                list.add(node.mRightChild);
            }
        }
    }

    public int layerOrderWithTravsersal(Node root,int level) {
        if (null == root || level < 0) {
            return 0;
        }
        if (level == 0) {
            System.out.println("data: "+root.mData);
            return 1;
        }
        return layerOrderWithTravsersal(root.mLeftChild, level - 1) + layerOrderWithTravsersal(root.mRightChild, level - 1);
    }


    public void posOrderNoTraversalOneStack(Node root) {
        Stack<Node> nodeStack = new Stack<Node>();
        Node pre = null;
        while (root != null || !nodeStack.isEmpty()) {
            while (root != null) {
                nodeStack.push(root);
                root = root.mLeftChild;
            }
            Node rChild = nodeStack.peek().mRightChild;
            if (null == rChild || rChild == pre) {//pop
                Node node = nodeStack.pop();
                System.out.println("--" + node.mData);
                pre = node;
                root = null;
            } else {//travseral right child
                root = rChild;
            }
        }
    }

    public void posOrderNoTraversalTwoStack(Node root) {
        Stack<Node> nodeStack = new Stack<Node>();
        Stack<Integer> flagStack = new Stack<Integer>();
        Integer flag = new Integer(1);
        while (root != null || !nodeStack.isEmpty()) {
            while (root != null) {
                nodeStack.push(root);
                flagStack.push(new Integer(0));
                root = root.mLeftChild;
            }
            while (!nodeStack.isEmpty() && flagStack.peek().equals(flag)) {
                Node node = nodeStack.pop();
                System.out.println("--" + node.mData);
                flagStack.pop();
            }
            if (!nodeStack.isEmpty()) {
                root = nodeStack.peek();
                root = root.mRightChild;
                flagStack.pop();
                flagStack.push(new Integer(1));
            }
        }
    }


    public boolean isBlance(Node root) {
        if(null == root)
            return false;
        int leftH = getDepth(root.mLeftChild);
        int rigH = getDepth(root.mRightChild);
        if (Math.abs(leftH - rigH) > 1) {
            return false;
        }else {
            return isBlance(root.mLeftChild) && isBlance(root.mRightChild);
        }
    }

    public boolean isBlance2(Node root) {
        if(null == root)
            return true;
        if(isBlance(root.mLeftChild) && isBlance(root.mRightChild)){
            int leftH = getDepth(root.mLeftChild);
            int rigH = getDepth(root.mRightChild);
            if (Math.abs(leftH - rigH) > 1) {
                return false;
            }else {
                return true;
            }
        }else {
            return false;
        }
    }


    public int getMaxDepth(Node root) {
        if (root == null) {
            return 0;
        }
        int ld = getDepth(root.mLeftChild);
        int rd = getDepth(root.mRightChild);
        return ld + rd;
    }

    public int getDepth(Node root) {
        if(null == root)
            return 0;
        int leftH = getDepth(root.mLeftChild) + 1;
        int heightH = getDepth(root.mRightChild) + 1;
        return leftH > heightH ? leftH : heightH;
    }

    public int getKLayerCount(Node root, int k) {
        int level = 0;
        int count = 0;
        LinkedList<Node> queue = new LinkedList<Node>();
        queue.push(root);
        count = level = 1;
        int index = 0;
        int nextCount = 0;
        while (index < count) {
            Node node = queue.pop();
            if (null != node.mLeftChild) {
                queue.push(node.mLeftChild);
                nextCount++;
            }
            if (null != node.mRightChild) {
                queue.push(node.mRightChild);
                nextCount++;
            }
            if ((index + 1) >= count) {
                if (level == k - 1) {
                    return nextCount;
                }
                count = nextCount;
                index = 0;
                level++;
                nextCount = 0;
            } else {
                index++;
            }
        }
        return 0;
    }

    public Node mirror(Node root) {
        if (root == null) {
            return null;
        }
        Node left = mirror(root.mLeftChild);
        Node right = mirror(root.mRightChild);
        root.setLeftChild(right);
        root.setRightChild(left);
        return root;
    }
}
