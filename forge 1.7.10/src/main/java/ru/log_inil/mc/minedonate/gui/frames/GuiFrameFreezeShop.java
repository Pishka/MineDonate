package ru.log_inil.mc.minedonate.gui.frames;

import java.awt.Color;

import net.minecraft.client.gui.GuiButton;

import ru.alastar.minedonate.gui.ShopGUI;
import ru.alastar.minedonate.rtnl.ModNetwork;
import ru.alastar.minedonate.rtnl.Utils;

import ru.log_inil.mc.minedonate.gui.DrawType;
import ru.log_inil.mc.minedonate.gui.GuiEntry;
import ru.log_inil.mc.minedonate.gui.GuiGradientButton;
import ru.log_inil.mc.minedonate.gui.GuiGradientTextField;
import ru.log_inil.mc.minedonate.localData.frames.DataOfUIFrameFreezeShop;

public class GuiFrameFreezeShop extends GuiEntry {

	int width = 200 ;
	int height = 40 ;
	
	int posX ;
	int posY ;
	
	static int backgroundColor = Utils . rgbaToInt ( new Color ( 0, 0, 0, 150 ) ) ;
	static int fieldBorderRedColor = Utils . rgbaToInt ( new Color ( 255, 0, 0, 150 ) ) ;
	static int fieldBorderColor = Utils . rgbaToInt ( new Color ( 255, 255, 255, 150 ) ) ;
	static int titleColor = Utils . rgbaToInt ( new Color ( 255, 255, 255, 180 ) ) ;
	
	int widthCenter = width / 2 ;
	int heightCenter = height / 2 ;
	
	DataOfUIFrameFreezeShop douiffs ;

	int shopId = -1 ;

	public GuiFrameFreezeShop ( DataOfUIFrameFreezeShop _douiffs ) {
		
		douiffs = _douiffs ;
		
		fieldText = douiffs . reasonField . text ;
		fieldHolder = douiffs . reasonField . textHolder ;
		
	}
	
    public void draw(ShopGUI g, int page, int mouseX, int mouseY, float partialTicks, DrawType dt ) {

    	g . drawRect ( posX, posY, posX + width, posY + height, backgroundColor ) ;
    	
    	super . draw( g, page, mouseX, mouseY, partialTicks, dt ) ;
    	
    	g . drawString ( g . getFontRenderer ( ), douiffs . title, posX + 5, posY + 3, titleColor ) ;
    	
    	reasonField . drawTextBox ( ) ;
    	
    	
    }
    
    GuiButton freezeChangesButton ;
    GuiButton cancelChangesButton ;
    GuiGradientTextField reasonField ; 
    
    String fieldText, fieldHolder ;
    
    @Override
	public void postShow ( ShopGUI g ) {
		
    	super . postShow ( g ) ;

    	posX = (g.getScaledResolution().getScaledWidth()/2) - widthCenter;
    	posY = (g.getScaledResolution().getScaledHeight()/2) - heightCenter;

    	if ( freezeChangesButton == null ) {
        	
    		freezeChangesButton = new GuiGradientButton ( ShopGUI . getNextButtonId ( ), posX, posY + height, 
    				douiffs.freezeButton.width, douiffs.freezeButton.height, douiffs.freezeButton.text, false ) ;
    	
    	}
    	
    	if ( this . isVisible ( ) ) {

    		g . addButton ( freezeChangesButton, false ) ;
    		
    	}
    	
    	if ( cancelChangesButton == null ) {
        	
    		cancelChangesButton = new GuiGradientButton ( ShopGUI . getNextButtonId ( ), posX, posY + height, 
    				douiffs.cancelButton.width, douiffs.cancelButton.height, douiffs.cancelButton.text, false ) ;
    	
    	}
    	
    	if ( this . isVisible ( ) ) {

    		g . addButton ( cancelChangesButton, false ) ;
    		
    	}

		if ( reasonField == null ) {
		
			reasonField = new GuiGradientTextField ( g.getFontRenderer(), 30, 10, douiffs . reasonField . width - 1, douiffs . reasonField . height, true ) ;
			reasonField . setMaxStringLength ( 140 ) ;
			reasonField . setEnableBorderDrawing ( true, fieldBorderColor ) ;
			
		}
		
		reasonField . setText ( fieldText != null ? fieldText : "" ) ;
		reasonField . setTextHolder ( fieldHolder ) ;
		
		reasonField . xPosition = posX + 20 ;
		reasonField . yPosition = posY + 15 ;

    	cancelChangesButton . yPosition = freezeChangesButton . yPosition = posY + height ;
    	cancelChangesButton . xPosition = posX + width - cancelChangesButton . width ;
    	freezeChangesButton . xPosition = cancelChangesButton . xPosition - freezeChangesButton . width ;

    	
    	
    }
    
    @Override
	public void unShow ( ShopGUI g ) {
		
    	super . unShow ( g ) ;
    	
    	g . removeButton ( cancelChangesButton ) ;
    	g . removeButton ( freezeChangesButton ) ;

	}
	
    @Override
	public boolean actionPerformed ( ShopGUI g, GuiButton b ) {
		
    	if ( b . id == freezeChangesButton . id ) {
    	
    		if ( reasonField . getText ( ) . trim ( ) . isEmpty ( ) ) {
    			
    			reasonField . fieldBorderColor = fieldBorderRedColor ;
    			
    			return false ;
    			
    		}
    		
    		g . setLoading ( true ) ;
    		
    		ModNetwork . sendToServerFreezeShopPacket ( this . shopId, reasonField . getText ( ) ) ;
    		
            g . showEntry ( "freezeShop", false ) ; 

            unShow ( g ) ;

    	}
    	
    	if ( b . id == cancelChangesButton . id ) {
    	
    		g . showEntry ( "freezeShop", false ) ;
    		unShow ( g ) ;
    		
    	}
    	
		return false ;
		
	}
	
    @Override
	public boolean onClick ( int x, int y, int i ) {
		
    	if ( reasonField != null ) {

    		reasonField . mouseClicked ( x, y, i ) ;
    		    		
    		reasonField . fieldBorderColor = fieldBorderColor ;

    	}
    	
		return false ;
		
	}

    @Override
	public boolean onKey ( char c, int k ) {
		
    	if ( reasonField != null && reasonField . isFocused ( ) ) {
    		
    		reasonField . textboxKeyTyped ( c, k ) ;
    		
    		reasonField . fieldBorderColor = fieldBorderColor ;

    		return true ;
    		
    	}
    	
		return false ;
		
	}
	
	public boolean isOwnerButton ( GuiButton gb ) {
		
		return ( gb == cancelChangesButton || gb == freezeChangesButton ) ;
		
	}
	
    @Override
	public boolean coordContains ( int x, int y ) {
		
		return ( posX <= x && x <= posX + width ) && ( posY <= y && posY + height >= y ) ;
		
	}

    @Override
	public boolean lockContextMenuUnderEntry ( ) {
		
		return isVisible ( ) ;
		
	}
	
    @Override
	public boolean lockButtonsUnderEntry ( ) {
		
		return isVisible ( ) ;
		
	}
	
    public void setFieldData ( String _text, String _holder ) {
    	
    	fieldText = _text ;
    	fieldHolder = _holder ;

    }

	public void setShopId ( int _shopId ) {

		shopId = _shopId ;
		
	}
    
}
