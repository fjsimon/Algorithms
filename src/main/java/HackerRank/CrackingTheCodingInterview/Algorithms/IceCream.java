package HackerRank.CrackingTheCodingInterview.Algorithms;


public class IceCream implements Comparable<Object> {

    public int cost;
    public int id;

    public IceCream(int cost, int index) {
        this.cost = cost;
        this.id = index;
    }

    @Override
    public int compareTo(Object o) {
        IceCream iceCream = (IceCream) o;

        return this.cost - iceCream.cost;
    }
    @Override
    public boolean equals(Object o){
        IceCream iceCream = (IceCream) o;

        return iceCream.cost == this.cost;
    }
}