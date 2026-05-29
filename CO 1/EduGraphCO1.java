// Save as: EduGraphCO1.java
// Run in VS Code:
// javac EduGraphCO1.java
// java EduGraphCO1


class BSTNode {

    int courseId;
    String courseName;
    BSTNode left, right;

    BSTNode(int id, String name) {
        courseId = id;
        courseName = name;
        left = right = null;
    }
}


// ---------------- BST ----------------

class BST {

    BSTNode insert(BSTNode root, int id, String name) {

        if (root == null) {
            return new BSTNode(id, name);
        }

        if (id < root.courseId) {
            root.left = insert(root.left, id, name);
        } else {
            root.right = insert(root.right, id, name);
        }

        return root;
    }

    BSTNode search(BSTNode root, int id) {

        if (root == null || root.courseId == id) {
            return root;
        }

        if (id < root.courseId) {
            return search(root.left, id);
        }

        return search(root.right, id);
    }

    void inorder(BSTNode root) {

        if (root != null) {

            inorder(root.left);

            System.out.println(root.courseId + " - " + root.courseName);

            inorder(root.right);
        }
    }
}


// ---------------- AVL NODE ----------------

class AVLNode {

    int courseId;
    String courseName;
    int height;

    AVLNode left, right;

    AVLNode(int id, String name) {

        courseId = id;
        courseName = name;
        height = 1;
    }
}


// ---------------- AVL TREE ----------------

class AVLTree {

    int height(AVLNode node) {

        if (node == null)
            return 0;

        return node.height;
    }

    int getBalance(AVLNode node) {

        if (node == null)
            return 0;

        return height(node.left) - height(node.right);
    }

    // RIGHT ROTATION
    AVLNode rightRotate(AVLNode y) {

        AVLNode x = y.left;
        AVLNode t2 = x.right;

        x.right = y;
        y.left = t2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    // LEFT ROTATION
    AVLNode leftRotate(AVLNode x) {

        AVLNode y = x.right;
        AVLNode t2 = y.left;

        y.left = x;
        x.right = t2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    // INSERT
    AVLNode insert(AVLNode root, int id, String name) {

        if (root == null) {
            return new AVLNode(id, name);
        }

        if (id < root.courseId) {
            root.left = insert(root.left, id, name);
        } else {
            root.right = insert(root.right, id, name);
        }

        root.height = 1 + Math.max(height(root.left), height(root.right));

        int balance = getBalance(root);

        // LEFT LEFT
        if (balance > 1 && id < root.left.courseId) {
            return rightRotate(root);
        }

        // RIGHT RIGHT
        if (balance < -1 && id > root.right.courseId) {
            return leftRotate(root);
        }

        // LEFT RIGHT
        if (balance > 1 && id > root.left.courseId) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // RIGHT LEFT
        if (balance < -1 && id < root.right.courseId) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    // SEARCH
    AVLNode search(AVLNode root, int id) {

        if (root == null || root.courseId == id) {
            return root;
        }

        if (id < root.courseId) {
            return search(root.left, id);
        }

        return search(root.right, id);
    }

    // DISPLAY
    void inorder(AVLNode root) {

        if (root != null) {

            inorder(root.left);

            System.out.println(root.courseId + " - " + root.courseName);

            inorder(root.right);
        }
    }
}


// ---------------- MAIN CLASS ----------------

public class EduGraphCO1 {

    public static void main(String[] args) {

        // BST
        System.out.println("===== EduGraph BST =====");

        BST bst = new BST();
        BSTNode bstRoot = null;

        bstRoot = bst.insert(bstRoot, 101, "Python");
        bstRoot = bst.insert(bstRoot, 105, "Machine Learning");
        bstRoot = bst.insert(bstRoot, 102, "Data Structures");

        System.out.println("\nBST Course Records:");
        bst.inorder(bstRoot);

        BSTNode bstSearch = bst.search(bstRoot, 105);

        if (bstSearch != null) {

            System.out.println("\nBST Search Result:");
            System.out.println(bstSearch.courseId + " - " + bstSearch.courseName);
        }


        // AVL TREE
        System.out.println("\n===== EduGraph AVL Tree =====");

        AVLTree avl = new AVLTree();
        AVLNode avlRoot = null;

        avlRoot = avl.insert(avlRoot, 101, "Python");
        avlRoot = avl.insert(avlRoot, 105, "Machine Learning");
        avlRoot = avl.insert(avlRoot, 102, "Data Structures");

        System.out.println("\nAVL Course Records:");
        avl.inorder(avlRoot);

        AVLNode avlSearch = avl.search(avlRoot, 102);

        if (avlSearch != null) {

            System.out.println("\nAVL Search Result:");
            System.out.println(avlSearch.courseId + " - " + avlSearch.courseName);
        }
    }
}