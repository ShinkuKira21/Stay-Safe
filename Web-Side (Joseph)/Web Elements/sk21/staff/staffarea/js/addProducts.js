function AvailabilityInf()
		{
			var bInfinity = document.getElementById("productInf"); 

			var inpAvailability = document.getElementById("productAvailable");

			//var txtAvailability = document.getElementById("txtAvailability");
			
			//var textAvailContainer = document.getElementById("txtAvailContainer");

			if(bInfinity.checked)                    
				inpAvailability.disabled = "disabled";

			else
				inpAvailability.disabled = null;
		}
		
		function ShowAllergies()
		{
			var containAllergies = document.getElementById("container-allergies");
            var containChkbox = document.getElementById("container-allergyChkbox");
            
			var bShowAllergiesChk1 = document.getElementById("formCheck-1");
			var bShowAllergiesChk2 = document.getElementById("formCheck-11");
            
            
            var clist = containAllergies.getElementsByTagName("input");
            
			if(!bShowAllergiesChk1.checked || bShowAllergiesChk2.checked)
            {
                if(bShowAllergiesChk2.checked)
                {
                    bShowAllergiesChk1.checked = false;
                    bShowAllergiesChk2.checked = false;
                }
                
                containChkbox.style.display = null;
				containAllergies.style.display = "none";
                for (var i = 0; i < clist.length; ++i) { clist[i].checked = false; }
            }
				
			else
            {
                containChkbox.style.display = "none";
				containAllergies.style.display = null;
            }
		}