package com.dream;
@Singleton
@Deprecated
public class RecommendatorImpl implements Recommendator {

    @InjectProperty("wisky")
    private String alcohol;

    public RecommendatorImpl(){
        System.out.println("recommendator was crated");
    }

    @Override
    public void recommend() {
        System.out.println("drink " + alcohol);
    }
}
