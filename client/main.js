window.onload = function() {
    selectAllItems();
}

function selectAllItems() {
	var xhttp = new XMLHttpRequest();
	xhttp.open("GET", "http://localhost:8080/jaxrs_hibernate/items");
	xhttp.onload = function() {
		var items = JSON.parse(xhttp.responseText);
		var rows = "";
		for (i = 0; i < items.length; i++) {
			rows +=
				"<tr>" +
				"<td>" + items[i].id + "</td>" +
				"<td>" + items[i].value + "</td>" +
				"</tr>";
		}
		document.getElementById("mainTable").innerHTML = rows;
	}
	xhttp.send();
}

function insertItem() {
    var itemToInsert = {
		value: document.getElementById('insertValue').value
	};
    var itemToInsertJson = JSON.stringify(itemToInsert);
    var xhttp = new XMLHttpRequest();
    xhttp.open('POST', 'http://localhost:8080/jaxrs_hibernate/items');
    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.onload = function() {
        selectAllItems();
    }
    xhttp.send(itemToInsertJson);
}

function updateItem() {
    var itemToUpdate = {
		value: document.getElementById('updateValue').value
	};
    var itemToUpdateJson = JSON.stringify(itemToUpdate);
    var id = document.getElementById('updateId').value;
    var xhttp = new XMLHttpRequest();
    xhttp.open('PUT', 'http://localhost:8080/jaxrs_hibernate/items/' + id);
    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.onload = function() {
        selectAllItems();
    }
    xhttp.send(itemToUpdateJson);
}

function deleteItem() {
    var id = document.getElementById('deleteId').value;
    var xhttp = new XMLHttpRequest();
    xhttp.open('DELETE', 'http://localhost:8080/jaxrs_hibernate/items/' + id);
    xhttp.onload = function() {
        selectAllItems();
    }
    xhttp.send();
}
