
package danh.util;

public class ApplicationConstants {
    // có bao nhiêu chức nằng thì xài bao nhiêu class con
    public class Middle_Features {
        public static final String LOGIN = "";
        public static final String CONTROLLER = "loginServlet";
        public static final String SEARCH_USER = "searchUserServlet";
        public static final String DELETE_USER = "deleteUserServlet";
        public static final String UPDATE_USER = "updateUserServlet";
        public static final String START_UP = "startUpServlet";
        public static final String SEARCH_SHOPPING = "searchShoppingServlet";
        public static final String PRINT_ALL_SHOPPING = "printAllProductServlet";
        public static final String REMOVE_PRODUCT = "removeProductServlet";
        public static final String ADD_TO_CART = "addToCartServlet";
        public static final String SIGN_UP = "signUpServlet";
        public static final String CHECK_OUT = "checkOutServlet";
        public static final String VIEW_CART = "viewCartPage";
        public static final String LOG_OUT = "logOutServlet";
    }
    
    public class DeleteUser_Features {
        public static final String ERROR_PAGE = "errorPage";
        
    }
    public class AddToCart_Features {
        public static final String PRINT_ALL_SHOPPING = "Middle_Servlet?btAction=showAllProduct";
    }
    public class LogIn_Features {
        public static final String INVALID_PAGE = "invalidPage";
        public static final String RESULT_PAGE = "searchPage";
    }
    
    public class PrintAllProducts_Features {
        public static final String RESULT_PAGE = "showShoppingPage";
        public static final String ERROR_PAGE = "errorPage";
    }
    
    public class RemoveProduct_Features {
        public static final String ERROR_PAGE = "errorPage"; 
        public static final String VIEW_CART = "viewCartPage";
    }
    
    public class SearchUser_Features {
        public static final String RESULT_PAGE = "searchPage";
        
    }
    
    public class SearchShopping_Features {
        public static final String SEARCH_PAGE = "Middle_Servlet?btAction=showAllProduct";
        public static final String RESULT_PAGE = "shoppingPage";
    }
    
    public class SignUp_Features {
        public static final String ERROR_PAGE = "errorNewAcc";
        public static final String LOGIN_PAGE = "";
    }
    
    public class StartUp_Features {
        public static final String LOGIN_PAGE = "";
        public static final String SEARCH_PAGE = "searchPage";
    }
    
    public class UpdateUser_Features {
        public static final String ERROR_PAGE = "errorPage";
    }
    
    public class LogOut_Features {
        public static final String LOGIN_PAGE = "";
    }
    
    public class CheckOut_Features {
        public static final String ERROR_PAGE = "errorPage";
        public static final String SHOW_PAGE = "printOrderPage";
        public static final String VIEW_CART = "viewCartPage";
    }
}
