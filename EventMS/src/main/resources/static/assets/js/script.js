
 function  validatealpha(event)
 { 
    var charCode = event.keyCode;

           if ((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123) || charCode == 8 || charCode == 32 || charCode == 46)

               return true;
           else
               return false;
 }
 function sleep(delay) {
     var start = new Date().getTime();
     while (new Date().getTime() < start + delay);
 }
 function checkEmail(email) { 
   var re = /\S+@\S+\.\S+/;
       return re.test(email);
 }

 function validatemobile(evt)
 {
   evt = (evt) ? evt : window.event;
   var charCode = (evt.which) ? evt.which : evt.keyCode;
   if (charCode > 31 && (charCode < 48 || charCode > 57)) {
       return false;
   }
   return true;
 }
 function loadstatecombo() {  
 jQuery.ajax({
 url: "/loadstates",  
 success:function(data){
 $("#state").html(data.options);  
 $("#state").trigger('options-loaded');
 },
 error:function (){}
 });
 }

 function loadcitiescombo() { 
 if($("#state").val()=="") return;
 $("#city").hide();
 jQuery.ajax({
 url: "/loadcities/"+$("#state").val(),  
 success:function(data){
 $("#city").html(data.options);
  $("#city").show();

  $("#city").trigger('options-loaded');
 },
 error:function (){}
 });
 }

 var ajaxfrm;
 (function ($) {  
 $(".ajaxfrm").off( "submit");
 $(".ajaxfrm").submit(function(e) {

    e.preventDefault(); // avoid to execute the actual submit of the form.
     
    var form = $(this);
    var url = form.attr('action');  
    var data = new FormData(this);
    ajaxfrm=form;
    $(ajaxfrm).find('button').attr("disabled",true);
    $.ajax({
           type: "POST",
           url: url,  
           data:data , // serializes the form's elements.
 		    dataType: "json",
 		  cache: false,
 		  contentType: false,
 		  processData: false,
           success: function(data)
           { 
 			$(ajaxfrm).find('.msg').removeClass("d-none").addClass("d-none");
 		      if(data.success)
                {
 		    	  $(ajaxfrm).find('.msg-success').removeClass("d-none").html(data.message);
 		    	  const myTimeout = setTimeout(function() {if($(ajaxfrm).attr("data-return"))document.location.href=$(ajaxfrm).attr("data-return");}, 3000);
			 
			  } // show response from the php script.
 			  else
 				  { 
 					$(ajaxfrm).find('.msg-error').removeClass("d-none").html(data.message);
 				  
 				  }
			  $(ajaxfrm).find('button').attr("disabled",false);
 		      }
         });
 		     });  
 })(jQuery); 