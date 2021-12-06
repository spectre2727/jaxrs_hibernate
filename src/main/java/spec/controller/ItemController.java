package spec.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import spec.entity.Item;
import spec.repository.ItemRepository;

@Path("/items")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ItemController {
	
	private ItemRepository itemRepository = new ItemRepository();
	
	@GET
	public List<Item> selectAllItems() {
		return itemRepository.selectAllItems();
	}
	
	@POST
	public int insertItem(Item item) {
		return itemRepository.insertItem(item);
	}
	
	@PUT
	@Path("/{id}")
	public int updateItem(@PathParam("id") String id, Item item) {
		return itemRepository.updateItem(id, item);
	}

	@DELETE
	@Path("/{id}")
	public int deleteItem(@PathParam("id") String id) {
		return itemRepository.deleteItem(id);
	}

}
