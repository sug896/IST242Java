class Tree {
    double heightFt;
    double trunkDiameterInches;
    TreeType treeType;

    Tree(double heightFt, double trunkDiameterInches, TreeType type) {
        this.heightFt = heightFt;
        this.trunkDiameterInches = trunkDiameterInches;
        this.treeType = type;
    }


    static void announceTree(){
        System.out.println("don't know what tree it is");
    }
}
