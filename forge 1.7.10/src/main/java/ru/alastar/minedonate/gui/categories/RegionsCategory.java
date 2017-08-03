package ru.alastar.minedonate.gui.categories;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import ru.alastar.minedonate.MineDonate;
import ru.alastar.minedonate.gui.BuyButton;
import ru.alastar.minedonate.gui.ShopCategory;
import ru.alastar.minedonate.gui.ShopGUI;
import ru.alastar.minedonate.merch.Merch;
import ru.alastar.minedonate.merch.info.RegionInfo;
import ru.log_inil.mc.minedonate.gui.DrawType;
import ru.log_inil.mc.minedonate.gui.GuiScrollingList;
import ru.log_inil.mc.minedonate.gui.painters.RegionGridItemPainter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

/**
 * Created by Alastar on 20.07.2017.
 */
public class RegionsCategory implements ShopCategory {

	int catId = 2 ;

    private static int m_Per_Row = 4;
    private static int m_Per_Col = 2;

    RegionGridItemPainter rip ;
    public RegionsCategory ( ) {
    	
    	 rip = new RegionGridItemPainter ( this ) ;
		 
    }
    
    @Override
    public boolean getEnabled() {
        return MineDonate.cfg.sellRegions;
    }

    @Override
    public int getSourceCount(int shopId) {
        return list . size ( )  ; // MineDonate.m_Categories[2].getMerch().length;
    }

    @Override
    public String getName() {
        return "Regions";
    }
    
    @Override
    public int elements_per_page() {
        return m_Per_Col * m_Per_Row;
    }

    @Override
    public void actionPerformed(GuiButton button) {
        
    }
    
    @Override
    public void draw(ShopGUI relative, int m_Page, int mouseX, int mouseY, float partialTicks, DrawType dt) {
    	
		ScaledResolution resolution = new ScaledResolution( relative.mc, relative.mc.displayWidth, relative.mc.displayHeight);
		
    	if ( dt == DrawType . BG ){
    		
      	    // relative.drawRect(30, (int) (resolution.getScaledHeight() * 0.1) + 15+24, resolution.getScaledWidth()-30,  (int) ( (resolution.getScaledHeight()) - (resolution.getScaledHeight() * 0.1) ) - 5, 1258291200);
    	    relative.drawGradientRectAccess(30, (int) (resolution.getScaledHeight() * 0.1) + 15+24, resolution.getScaledWidth()-30,  (int) ( (resolution.getScaledHeight()) - (resolution.getScaledHeight() * 0.1) ) - 5,  -1072689136, -804253680);

    	} else if ( dt == DrawType.POST ) {
        	
    		GL11 . glEnable ( GL12 . GL_RESCALE_NORMAL ) ;
    		RenderHelper . enableGUIStandardItemLighting ( ) ;
    		
            rip . drawn = 0 ;
            
            //ArrayList list0 = new ArrayList();
            for (int i = 0; i < m_Per_Row; ++i) {
                for (int j = 0; j < m_Per_Col; ++j) {
                    if (m_Page * m_Per_Col * m_Per_Row + rip . drawn < list . size ( ) ) {
               
                    	rip.draw(relative, resolution, m_Page, mouseX, mouseY, partialTicks, list.get(m_Page * m_Per_Col * m_Per_Row + rip.drawn), i, j);
                    	
                    	rip . drawn ++ ;
                    	
                    }
                }
            }
            
    		RenderHelper . disableStandardItemLighting ( ) ;
    		GL11 . glDisable ( GL12 . GL_RESCALE_NORMAL ) ;
    		//if(list0.size() > 0)
            //    relative.drawHoveringText(list0, mouseX, mouseY, relative.getFontRenderer());
    		
    		
    		//
    		
    	    GL11.glEnable(GL11.GL_BLEND);
  	        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
  	        GL11.glDisable(GL11.GL_ALPHA_TEST);
  	        GL11.glShadeModel(GL11.GL_SMOOTH);
  	        GL11.glDisable(GL11.GL_TEXTURE_2D);
	        
	        //
	        
	        Tessellator var18 = Tessellator.instance;
	        byte var20 = 12;
	        
	        // 30, (int) (resolution.getScaledHeight() * 0.1) + 15+24, resolution.getScaledWidth()-30,  (int) ( (resolution.getScaledHeight()) - (resolution.getScaledHeight() * 0.1) ) - 5,  -1072689136, -804253680);

	        var18.startDrawingQuads();
	        
	        var18.setColorRGBA_I(0, 0);
	        
	        var18.addVertexWithUV((double)30, (double)((int) (resolution.getScaledHeight() * 0.1) + 19+20+ var20), 0.0D, 0.0D, 1.0D);
	        var18.addVertexWithUV((double)(int) resolution.getScaledWidth()-30, (double)( (resolution.getScaledHeight() * 0.1) + 19+20 + var20), 0.0D, 1.0D, 1.0D);
	        
	        var18.setColorRGBA_I(0, 255);
	        
	        var18.addVertexWithUV((double)resolution.getScaledWidth()-30, (double)(int) (resolution.getScaledHeight() * 0.1) + 19+20, 0.0D, 1.0D, 0.0D);
	        var18.addVertexWithUV((double)30, (double)(int) (resolution.getScaledHeight() * 0.1) + 19+20, 0.0D, 0.0D, 0.0D);
	        
	        var18.draw();

	        
	        var18.startDrawingQuads();
	        
	        var18.setColorRGBA_I(0, 255);
	        
	        var18.addVertexWithUV((double)30, (double) ( (resolution.getScaledHeight()) - (resolution.getScaledHeight() * 0.1) ) - 6, 0.0D, 0.0D, 1.0D);
	        var18.addVertexWithUV((double)resolution.getScaledWidth()-30, (double) ( (resolution.getScaledHeight()) - (resolution.getScaledHeight() * 0.1) ) - 6, 0.0D, 1.0D, 1.0D);
	        
	        var18.setColorRGBA_I(0, 0);
	        
	        var18.addVertexWithUV((double)resolution.getScaledWidth()-30, (double)( ( (resolution.getScaledHeight()) - (resolution.getScaledHeight() * 0.1) ) - 6 - var20), 0.0D, 1.0D, 0.0D);
	        var18.addVertexWithUV((double)30, (double)( ( (resolution.getScaledHeight()) - (resolution.getScaledHeight() * 0.1) ) - 6 - var20), 0.0D, 0.0D, 0.0D);
	        
	        var18.draw();
	        
	        //
	        
	        GL11.glEnable(GL11.GL_TEXTURE_2D);
	        GL11.glShadeModel(GL11.GL_FLAT);
	        GL11.glEnable(GL11.GL_ALPHA_TEST);
	        GL11.glDisable(GL11.GL_BLEND);
	        
        }
 
    }
    
    List < RegionInfo > list = new ArrayList <RegionInfo > ( ) ;
    Map < Integer, BuyButton > buttonsMap = new HashMap < Integer, BuyButton> ( ) ; // holy shi~

    int lastPage = 0 ;
    BuyButton bb ;
    
    @Override
    public void updateButtons ( ShopGUI relative, int m_Page ) {
        
		for ( RegionInfo ri : list ) {
			
			if ( buttonsMap . containsKey ( ri.merch_id ) ) relative . removeButton ( buttonsMap . get ( ri . merch_id ) ) ;

    	}
		
		buttonsMap . clear ( ) ;
    	list . clear ( ) ;
    	
		if ( MineDonate . shops . containsKey ( gui . getCurrentShopId ( ) ) ) {

	    	if ( search ) {
	    		
	    		for ( Merch ri:  MineDonate . shops . get ( gui . getCurrentShopId ( ) ) . cats [ catId ] . getMerch ( ) ) {
	    			
	        		if ( ( ( RegionInfo ) ri ) . name . contains ( searchValue ) ) {
	        			
	        			list . add ( ( RegionInfo ) ri ) ;
	        			
	        		}
	        		
	        	}
	    		
	    	} else {
	    		
	    		for ( Merch ri:  MineDonate . shops . get ( gui . getCurrentShopId ( ) ) . cats [ catId ] . getMerch ( ) ) {
	    			
	    			list . add ( ( RegionInfo ) ri ) ;
	  
	        	}
	    		
	    		lastPage = m_Page ; 
	    		
	    	}
		
		}
		
    	int drawn = 0;
        //MineDonate.m_Categories[2].getMerch().length
        ScaledResolution resolution= new ScaledResolution( relative.mc, relative.mc.displayWidth, relative.mc.displayHeight);

        for (int i = 0; i < m_Per_Row; ++i) {
            for (int j = 0; j < m_Per_Col; ++j) {
                if (m_Page * m_Per_Col * m_Per_Row + drawn < list.size()) {
 
                	int x_offset = ( ( resolution . getScaledWidth ( ) / 2 - ( getColCount ( ) * 75 ) / 2 ) / 2 ) + 75 * ( j + 1 );
                	int y_offset = ( ( resolution . getScaledHeight ( ) / 2 - ( getRowCount ( ) * 75 ) / 2 ) / 2 ) + 75 * ( i + 1 );
                
                    final RegionInfo info = list.get(m_Page * m_Per_Col * m_Per_Row + drawn);
                  
                    bb = new BuyButton(info . getShopId ( ), info.merch_id, ShopGUI.getNextButtonId(), x_offset - 22, y_offset + 15, MineDonate.cfgUI.cats.regions.itemBuyButton.width, MineDonate.cfgUI.cats.regions.itemBuyButton.height, MineDonate.cfgUI.cats.regions.itemBuyButton.text);
                    buttonsMap.put(info.merch_id, bb);

                    relative.addBtn(bb);
                    
                    ++drawn;
                    
                }
            }
        }
    }
    
    // #LOG
    
	@Override
	public int getButtonWidth ( ) {
		
		return MineDonate.cfgUI.cats.regions.categoryButtonWidth ;
		
	}
	
	@Override 
	public String getButtonText ( ) {
		
		return MineDonate.cfgUI.cats.regions.categoryButtonText ;
		
	}
	
	
	@Override
	public int getRowCount() {
		return m_Per_Row;
	}

	@Override
	public void setRowCount(int i) {
		m_Per_Row = i;
	}

	@Override
	public int getColCount() {
		return m_Per_Col;
	}

	@Override
	public void setColCount(int i) {
		m_Per_Col = i;
	}

	@Override
	public int getItemWidth() {
		
		return Math.max(MineDonate.cfgUI.cats.regions.itemBuyButton.width, 75);

	}

	@Override
	public int getItemHeight() {
		
		return Math.max(MineDonate.cfgUI.cats.regions.itemBuyButton.height, 95);
		
	}

	ShopGUI gui ;
	
	@Override
	public void init(ShopGUI shopGUI) {
		
		gui = shopGUI ;
		
	}

	@Override
	public void initGui() {
	}

	boolean search = false ;
	String searchValue = "" ;
	
	@Override
	public void search ( String text ) {
		
		search = ! ( text == null || text . trim ( ) . isEmpty ( ) ) ;
		
		if ( search ) {
			
			searchValue = text . toLowerCase ( ) . trim ( ) ;
			
		} else {
			
			searchValue = "" ;
			
		}
			
		updateButtons ( gui, 0 ) ;
		
	}
	
	@Override
	public GuiScrollingList getScrollList() {
		return null;
	}
	
}