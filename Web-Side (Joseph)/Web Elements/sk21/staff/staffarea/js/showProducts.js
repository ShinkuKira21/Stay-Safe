//Function will be called on page load
// Function should hide category containers.
var active = false;

function LoadCategories()
{
	//Get Category element
	var categoryContainers = document.getElementsByClassName('categories');
	var categoryRows = null;

	//Should show 3 categories
	//console.log(categoryContainers.length);

	for(var i = 0; i < categoryContainers.length; i++)
	{
		//Get child class by name row
		categoryRows = categoryContainers[i].getElementsByClassName('row');
		//Removes active attribute as nothings open
		categoryContainers[i].removeAttribute("active");
		//Set each child class row to none.
		for(var j = 0; j < categoryRows.length; j++)
			categoryRows[j].style.display = "none";
	}
}

// Show Category (string container)
// displays rows nested in category container
function ShowCategory(containerID)
{
	//Get Container Element and Current Active
	var categoryContainer = document.getElementById(containerID);
	var categoryRows = categoryContainer.getElementsByClassName('row');
	var currentActivity = categoryContainer.getAttribute("active");

	//Clears all categories on screen (process of switching containers)
	LoadCategories();

	//Display chosen category if currentActivity is null
	if(currentActivity == null)
	{
		//Sets active attribute to active container
		categoryContainer.setAttribute("active", "");
		for(var i = 0; i < categoryRows.length; i++)
				categoryRows[i].style.display = null;
	}
}

//Hide Container (Mainly Edit Form)
function HideContainer(containerID)
{
	var container = document.getElementById(containerID);
	container.remove();
}

//Real Time Input Information Sifference
function InputDiffValue(containerID, subContainerID, currentPrice = 0)
{
	var priceInp = document.getElementById(containerID);
	var diffSpan = document.getElementById(subContainerID);
	var diff = 0;
	
	//find difference of current price over new price
	diff = parseFloat(priceInp.value) - parseFloat(currentPrice);
	
	//if diff is set as NaN then set diff to currentPrice
	if(isNaN(diff)) diff = 0;

	//print 2 decimal places
	diffSpan.innerHTML = diff.toFixed(2);
}