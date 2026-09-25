package main

import (
	"fmt"
)

type Node struct {
	next *Node
	id int
}

func NewNode(next *Node, id int) *Node {
	return &Node{
		next: next,
		id: id,
	}
}

func (node *Node) getInfo() *Node{
	return &Node{
		next: node.next,
		id: node.id,
	}
}

func main(){
	node3 := NewNode(nil, 1);
	node2 := NewNode(node3, 2)
	node1 := NewNode(node2, 3)
	fmt.Println(node1.getInfo().id)
	fmt.Println(node1.getInfo().next.id)
	fmt.Println(node1.getInfo().next.next.id)
}