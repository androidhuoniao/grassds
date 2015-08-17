package ds;

/**
 * Created by grass on 7/24/15.
 */
public class Node {
    public static final int LEFT_CHILD = 0;
    public static final int RIGHT_CHILD = 1;
    public static final int NO_CHILD = 2;
    public int mData;
    public int mBH;
    public Node mLeftChild;
    public Node mRightChild;
    public Node mParent;
    private boolean mLeftTag;
    private boolean mRightTag;
    private boolean mIsTaller;

    public Node(int data) {
        mData = data;
    }

    public Node(int data, Node parent) {
        mData = data;
        mParent = parent;
    }

    public void setLeftChild(Node node) {
        mLeftChild = node;
        if (null != mLeftChild) {
            mLeftChild.mParent = this;
        }
    }

    public void setRightChild(Node node) {
        mRightChild = node;
        if (null != mRightChild) {
            mRightChild.mParent = this;
        }
    }

    public int getLoc(Node child) {
        if (null != child) {
            if (mLeftChild == child) {
                return LEFT_CHILD;
            }else if (mRightChild == child) {
                return RIGHT_CHILD;
            }
        }
        return NO_CHILD;
    }

    public void free(){
        mParent = mLeftChild = mRightChild = null;
        mData = 0;
    }

    public boolean isBigger(int data) {
        return mData > data;
    }

    @Override
    public String toString() {
        return "data: " + mData + " bh: " + mBH + " parent: "
                + (mParent != null ? mParent.mData : "null");
    }
}
