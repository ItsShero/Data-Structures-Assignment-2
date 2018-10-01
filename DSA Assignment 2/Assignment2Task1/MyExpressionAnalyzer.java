/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment2Task1;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author Matthew Hopkins 16928187
 */
public class MyExpressionAnalyzer {

    public static char c[];
    protected static Stack a = new Stack();

    public static void main(String[] args) {

        TreeNode myBTree = new TreeNode();
        Scanner scanner = new Scanner(System.in);

        String userInput = scanner.nextLine();

        c = userInput.toCharArray();

        replaceVariables();
        if (validateExpression()) {
            PopulateBinaryTree(myBTree);
            inOrderTraverse(myBTree);
            System.out.println("Stack");
            postTraverseStack(myBTree);
            System.out.println("Answer: " + a.peek());
        } else {
            System.out.println("Expression is not valid");
        }

    }

    /*
    This is used to make sure the user inputs a correct expression without errors
    Post: returns true if the expression is correct
     */
    public static boolean validateExpression() {
        boolean isValid = false;
        int openParenth = 0;
        int closedParenth = 0;

        Scanner scanner = new Scanner(System.in);
        
        //counts the number of parenthesis
        for (int k = 0; k < c.length; k++) {
            if (c[k] == '(') {
                openParenth++;
            }
            if (c[k] == ')') {
                closedParenth++;
            }

            //checks the left and right of the operators to ensure it is a number or parenthesis
            try {
                if ((c[k] == '+' || c[k] == '-' || c[k] == '*' || c[k] == '/')) {
                    if (((((c[k - 1] >= 48) && (c[k - 1] <= 57)) || c[k - 1] == ')') && (((c[k + 1] >= 48) && (c[k + 1] <= 57)) || c[k + 1] == '('))) {
                        isValid = true;
                    }
                }

            } catch (ArrayIndexOutOfBoundsException exception) {
                return false;
            }

            if (c[k] == '/' && c[k + 1] == '0') {
                return false;
            }
        }
        /*
        makes sure the last check is to check the correct amount of open parenthesis
        matches the amount of closed parenthesis  
        */
        if (openParenth == closedParenth && isValid == true) {
            isValid = true;
        } else {
            isValid = false;
        }

        return isValid;

    }

    
    /*
    Pre: user inputs an expression with variables
    Post: has user input actual numbers instead of variables for the expression
    */
    public static void replaceVariables() {
        Scanner scanner = new Scanner(System.in);
        for (int k = 0; k < c.length; k++) {
            if (((c[k] >= 65) && (c[k] <= 90)) || ((c[k] >= 97) && (c[k] <= 122))) {
                System.out.println("Input number for variable " + c[k]);
                c[k] = scanner.next().charAt(0);
            }
        }
    }
    
    /*
    populates the binary tree in-order
    */
    public static void PopulateBinaryTree(TreeNode myBTree) {
        TreeNode parent = myBTree;

        for (int k = 0; k < c.length; k++) {
            parent = myBTree;
            try {
                if (c[k] == '+' || c[k] == '-' || c[k] == '*' || c[k] == '/') {
                    myBTree.data = c[k];
                    if ((c[k - 1] >= 48) && (c[k - 1] <= 57) && c[k + 1] != '(') {

                        myBTree.left = new TreeNode(c[k - 1], myBTree);
                        myBTree.right = new TreeNode(c[k + 1], myBTree);
                        myBTree = parent.right;
                    }
                }
            } catch (ArrayIndexOutOfBoundsException exception) {

            }
        }
    }
        
    /*
    puts the numbers in a post-ordered stack and solves the equation as it is put into the stack
    */
    public static void postTraverseStack(TreeNode myBTree) //post: Traverse the Tree postOrder and solves it
    {
        if (myBTree == null) {
            return;
        }
        postTraverseStack(myBTree.left);
        postTraverseStack(myBTree.right);
        a.push(myBTree.data);
        if (!a.isEmpty()) {
            System.out.println(a.peek());
        }

        if (a.peek().equals('+')) {
            a.pop();
            int poptop = Integer.parseInt(a.pop().toString());
            int popsecond = Integer.parseInt(a.pop().toString());
            a.push(popsecond + poptop);
        } else if (a.peek().equals('-')) {
            a.pop();
            int poptop = Integer.parseInt(a.pop().toString());
            int popsecond = Integer.parseInt(a.pop().toString());
            a.push(popsecond - poptop);
        } else if (a.peek().equals('*')) {
            a.pop();
            int poptop = Integer.parseInt(a.pop().toString());
            int popsecond = Integer.parseInt(a.pop().toString());
            a.push(popsecond * poptop);
        } else if (a.peek().equals('/')) {
            a.pop();
            int poptop = Integer.parseInt(a.pop().toString());
            int popsecond = Integer.parseInt(a.pop().toString());
            a.push(popsecond / poptop);
        }
    }

    public static void inOrderTraverse(TreeNode myBTree) //post: Traverse the Tree inOrder and prints it
    {
        if (myBTree == null) {
            return;
        }
        inOrderTraverse(myBTree.left);
        System.out.println(myBTree.data);
        inOrderTraverse(myBTree.right);
    }

    public static void postTraverse(TreeNode myBTree) //post: Traverse the Tree inOrder and prints it
    {
        if (myBTree == null) {
            return;
        }
        postTraverse(myBTree.left);
        postTraverse(myBTree.right);
        System.out.println(myBTree.data);
    }
}
