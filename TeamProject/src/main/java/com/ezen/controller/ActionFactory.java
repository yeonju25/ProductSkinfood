package com.ezen.controller;

import javax.servlet.RequestDispatcher;

import com.ezen.action.Action;
import com.ezen.action.ProductBuyAction;
import com.ezen.action.ProductListAction;
import com.ezen.action.ProductSearchAction;
import com.ezen.action.ProductViewAction;

//Handler Mapping
public class ActionFactory {
    private  static ActionFactory instance = new ActionFactory();
    private ActionFactory(){}
    public static ActionFactory getInstance(){
        return instance;
    }
    public Action getAction(String command) {
        Action action = null;
        
       
        if(command.equals("product_list")) {
            action = new ProductListAction();
        }else if(command.equals("product_view")) {
        	action = new ProductViewAction();	
        }else if(command.equals("product_buy")) {
        	action = new ProductBuyAction();
        }else if(command.equals("product_search")) {
        	action = new ProductSearchAction();
        }
/*        } else if(command.equals("board_add")){
            action = new BoardAddAction();
        } else if(command.equals("board_write_form")){
            action = new BoardWriteAction();
        }else if(command.equals("board_write")){
            action = new BoardWriteForm();
        }else if(command.equals("board_view")){
            action = new BoardViewAction();
        }else if(command.equals("board_check_pass_form")) {
            action = new BoardCheckPassForm();
  
        /*
        * else if (equals.("board_update")
        * action = new BoardUpdateAction();
        * */
        return action;
    }
}
