package automannn.com.testmvp.presenter;


import automannn.com.testmvp.model.api.IModel;
import automannn.com.testmvp.view.api.IView;

public abstract class BasePresenter<M extends IModel,V extends IView>{

   protected M currModel;
   protected V currView;

    public BasePresenter(V view){
        this.currView = view;
    }

    public abstract void init();

}
