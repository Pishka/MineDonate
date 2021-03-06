package ru.alastar.minedonate.network.manage.handlers;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

import net.minecraft.entity.player.EntityPlayerMP;

import ru.alastar.minedonate.MineDonate;
import ru.alastar.minedonate.network.INetworkTask;
import ru.alastar.minedonate.network.manage.packets.CreateNewShopPacket;
import ru.alastar.minedonate.network.manage.packets.ManageResponsePacket;
import ru.alastar.minedonate.rtnl.ModShopManager;
import ru.alastar.minedonate.rtnl.ModNetworkTaskProcessor;
import ru.alastar.minedonate.rtnl.common.Account;

public class CreateNewShopServerPacketHandler implements IMessageHandler < CreateNewShopPacket, IMessage >, INetworkTask < CreateNewShopPacket, IMessage > {
	
    public CreateNewShopServerPacketHandler ( ) {

    }

    @Override
    public IMessage onMessage ( CreateNewShopPacket message, MessageContext ctx ) {
    	
    	ModNetworkTaskProcessor . processTask ( ( INetworkTask ) this, message, ctx ) ;

    	return null ;
    	
    }
    
    @Override
    public IMessage onMessageProcess ( CreateNewShopPacket message, MessageContext ctx ) {

		EntityPlayerMP serverPlayer = ctx . getServerHandler ( ) . playerEntity ;
		
		Account acc = MineDonate . getAccount ( serverPlayer ) ;
		
		if ( acc == null ) {
			
			return new ManageResponsePacket ( ManageResponsePacket.ResponseType.SHOP, ManageResponsePacket.ResponseCode.CREATE, ManageResponsePacket.ResponseStatus.ERROR_UNKNOWN ) ;

		}
		
		if ( acc . canCreateShop ( ) ) {
			
			if ( acc . freezedShopCreate ( ) ) {

				return new ManageResponsePacket ( ManageResponsePacket.ResponseType.SHOP, ManageResponsePacket.ResponseCode.CREATE, ManageResponsePacket.ResponseStatus.ERROR_SHOP_CREATE_BAN ) ;

			}
			
			if ( acc . limitShopCreate ( ) ) {

				return new ManageResponsePacket ( ManageResponsePacket.ResponseType.SHOP, ManageResponsePacket.ResponseCode.CREATE, ManageResponsePacket.ResponseStatus.ERROR_SHOP_CREATE_LIMIT ) ;

			}
			
			if ( message . name == null || message . name . isEmpty ( ) || message . name . length ( ) > 140 ) {

				return new ManageResponsePacket ( ManageResponsePacket.ResponseType.SHOP, ManageResponsePacket.ResponseCode.CREATE, ManageResponsePacket.ResponseStatus.ERROR_UNKNOWN ) ;

			}
			
			ModShopManager . createShop ( serverPlayer.getGameProfile().getId(), acc . name, message . name ) ;
			
	        return new ManageResponsePacket ( ManageResponsePacket.ResponseType.SHOP, ManageResponsePacket.ResponseCode.CREATE, ManageResponsePacket.ResponseStatus.OK ) ;

		} else {
		
	        return new ManageResponsePacket ( ManageResponsePacket.ResponseType.SHOP, ManageResponsePacket.ResponseCode.CREATE, ManageResponsePacket.ResponseStatus.ERROR_ACCESS_DENIED ) ;

		}
        
    }
    
}