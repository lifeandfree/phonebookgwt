package ru.project.phonebook.PhoneBookGwt.client;

import ru.project.phonebook.PhoneBookGwt.shared.FieldVerifier;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.widget.client.TextButton;

import java.util.ArrayList;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Random;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;

import java.util.Date;

import ru.project.phonebook.PhoneBookGwt.client.PhoneBookSet;;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class PhoneBookGwt implements EntryPoint {
	private FlexTable stocksFlexTable;
	private VerticalPanel mainPanel;
	private TextBox newSymbolTextBoxName;
	private TextBox newSymbolTextBoxPhoneNumber;
	private Button addButton;
	private Label lastUpdatedLabel;
	private ArrayList <String> stocks = new ArrayList<String>(); //Add this line
	private ArrayList<PhoneBookSet> phoneBookSets = new ArrayList<PhoneBookSet>();
	private static final int REFRESH_INTERVAL = 5000;
	
  /**
   * The message displayed to the user when the server cannot be reached or
   * returns an error.
   */
 // private static final String SERVER_ERROR = "An error occurred while "
 //     + "attempting to contact the server. Please check your network "
 //     + "connection and try again.";

  /**
   * Create a remote service proxy to talk to the server-side Greeting service.
   */
  //private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

  //private final Messages messages = GWT.create(Messages.class);
 
  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {

	  final ArrayList<PhoneBookSet> phoneBookSets = new ArrayList<PhoneBookSet>();
      
	  RootPanel rootPanel = RootPanel.get();
	  VerticalPanel mainPanel = new VerticalPanel();
	  
	  rootPanel.add(mainPanel, 10, 10);
	  mainPanel.setSize("253px", "280px");
	  
	  final FlexTable stocksFlexTable = new FlexTable();
	  
	//Add these lines
	  //stocksFlexTable.setText(0, 0, "№");
	  stocksFlexTable.setText(0, 0, "Name");
	  stocksFlexTable.setText(0, 1, "Phone Number");
	  stocksFlexTable.setText(0, 2, "Email");
	  stocksFlexTable.setText(0, 3, "Remove");
	  //stocksFlexTable.setText(0, 4, "Index");
	  
	  mainPanel.add(stocksFlexTable);
	  
	  HorizontalPanel addPanel = new HorizontalPanel();
	  mainPanel.add(addPanel);
	  
	  //TextBox newSymbolTextBox = new TextBox();
	  //addPanel.add(newSymbolTextBox);
	  
	  final TextBox newSymbolTextBoxName = new TextBox();
	  addPanel.add(newSymbolTextBoxName);
	  
	  final TextBox newSymbolTextBoxPhoneNumber = new TextBox();
	  addPanel.add(newSymbolTextBoxPhoneNumber);
	  
	  final TextBox newSymbolTextBoxEmail = new TextBox();
	  addPanel.add(newSymbolTextBoxEmail);
	  	  
	  Button addButton = new Button("Add");
	  addPanel.add(addButton);
	  
	  HorizontalPanel addPanelRemove = new HorizontalPanel();
	  mainPanel.add(addPanelRemove);
	  
	  final TextBox newSymbolTextBoxRemove = new TextBox();
	  addPanelRemove.add(newSymbolTextBoxRemove);
	  
	  Button addButtonRemove = new Button("Remove");
	  addPanelRemove.add(addButtonRemove);
	  
	  //Label lastUpdateLabel = new Label("New Label 1");
	  //mainPanel.add(lastUpdateLabel);
	  
	  newSymbolTextBoxRemove.addKeyPressHandler(new KeyPressHandler() {
		
		public void onKeyPress(KeyPressEvent event) {
			// TODO Auto-generated method stub
			if (event.getCharCode() == KeyCodes.KEY_ENTER) {
				//removePhoneNumber();
			}
		}
	});
	  
	  addButtonRemove.addClickHandler(new ClickHandler() {		

		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			removePhoneNumber();
		}

		private void removePhoneNumber() {
			// TODO Auto-generated method stub
			final String symbolRemove = newSymbolTextBoxRemove.getText().toUpperCase().trim();
		    //newSymbolTextBoxRemove.setFocus(true);
		    
		    if (phoneBookSets.contains(symbolRemove)) {
		    	phoneBookSets.remove(symbolRemove);
		    	int removedIndex = phoneBookSets.indexOf(symbolRemove);
		    	stocksFlexTable.removeRow(removedIndex + 1);
		    	Window.alert(symbolRemove + " removed");
		    	}
		    else {
		    	Window.alert(symbolRemove + " is not found");
			}
		    
		}
	});
	  
	  newSymbolTextBoxName.addKeyPressHandler(new KeyPressHandler() {
		
		public void onKeyPress(KeyPressEvent event) {
			// TODO Auto-generated method stub	
			if (event.getCharCode() == KeyCodes.KEY_ENTER){
				//addPhoneNumber();				
		}
	}
});
	  
	  newSymbolTextBoxPhoneNumber.addKeyPressHandler(new KeyPressHandler() {
			
		public void onKeyPress(KeyPressEvent event) {
			// TODO Auto-generated method stub	
			if (event.getCharCode() == KeyCodes.KEY_ENTER){
				//addPhoneNumber();			
		}
	}
});
		
			  
	  addButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				addPhoneNumber();
			}

			private void addPhoneNumber() {
				// TODO Auto-generated method stub
			    final String symbol = newSymbolTextBoxName.getText().toUpperCase().trim();
			    newSymbolTextBoxName.setFocus(true);
			    final String symbol2 = newSymbolTextBoxPhoneNumber.getText().toUpperCase().trim();
			    newSymbolTextBoxPhoneNumber.setFocus(true);
			    final String symbol3 = newSymbolTextBoxEmail.getText().toUpperCase().trim();
			    newSymbolTextBoxEmail.setFocus(true);
			    
			    if (!symbol.matches("^[0-9a-zA-Z\\.]{1,10}$")) {
			      Window.alert("'" + symbol + "' is not a valid symbol.");
			      newSymbolTextBoxName.selectAll();
			      
			      return;
			    }
			    
			    if (!symbol2.matches("^[0-9\\.]{1,10}$")) {
				      Window.alert("'" + symbol2 + "' is not a valid symbol.");
				      newSymbolTextBoxPhoneNumber.selectAll();
				      
				      return;
				    }

			    if (!symbol3.matches("^[0-9a-zA-Z\\.@]{1,10}$")) {
				      Window.alert("'" + symbol3 + "' is not a valid symbol.");
				      newSymbolTextBoxEmail.selectAll();
				      
				      return;
				    }
			    
			    newSymbolTextBoxName.setText("");
			    newSymbolTextBoxPhoneNumber.setText("");
			    newSymbolTextBoxEmail.setText("");
			    
			    
			    if (phoneBookSets.contains(symbol)){
			    	return;
			    }			        

			    if (phoneBookSets.contains(symbol2)){
			        return;
			    }

			    if (phoneBookSets.contains(symbol3)){
			        return;
			    }
			    
			    int row = stocksFlexTable.getRowCount();
			    phoneBookSets.add(new PhoneBookSet(symbol, symbol2, symbol3));
			    final PhoneBookSet set = phoneBookSets.get(phoneBookSets.size()-1);
			    stocksFlexTable.setText(row, 0, set.getName());
			    stocksFlexTable.setText(row, 1, set.getPhoneNumber());
			    stocksFlexTable.setText(row, 2, set.getEMail());
			    Button removeStock = new Button("x");
			    removeStock.addClickHandler(new ClickHandler() {
					
					public void onClick(ClickEvent event) {
						// TODO Auto-generated method stub
						//stocksFlexTable.
						int removedIndex = phoneBookSets.indexOf(set.getName());
						phoneBookSets.remove(removedIndex);
				        stocksFlexTable.removeRow(removedIndex + 1);
						
					}
				});
			    stocksFlexTable.setWidget(row, 3, removeStock);
			}

		});
	  
	// setup timer to refresh list automatically
	  Timer refreshTimer = new Timer() {
	  	public void run()
	  	{
	  		//refreshWatchList();
	  	}
	  };
	  refreshTimer.scheduleRepeating(REFRESH_INTERVAL);


  }

}
