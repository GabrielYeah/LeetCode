// Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

// push(x) -- Push element x onto stack.
// pop() -- Removes the element on top of the stack.
// top() -- Get the top element.
// getMin() -- Retrieve the minimum element in the stack.

// Aced.
class MinStack {
    
    List<StackNode> data = new ArrayList<StackNode>();
    
    public void push(int x) {
        int minValue = x;
        if (data.size() > 0)
            minValue = Math.min(x, data.get(data.size() - 1).minValue);
        data.add(new StackNode(x, minValue));
    }

    public void pop() {
        if (data.size() > 0)
            data.remove(data.size() - 1);
    }

    public int top() {
        if (data.size() > 0)
            return data.get(data.size() - 1).val;
        return 0;
    }

    public int getMin() {
        if (data.size() > 0)
            return data.get(data.size() - 1).minValue;
        return 0;
    }
}

class StackNode {
    int minValue;
    int val;
    public StackNode(int val, int minValue) {
        this.val = val;
        this.minValue = minValue;
    }
}
