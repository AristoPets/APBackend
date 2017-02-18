$(function() {
    $('#selectSearchBy').change(function(){
        $('#main_search').attr('action', $(this).val());
    });

    var options = {
        url: "http://aristo-pets.com/api/breeds",
        getValue: "name",
        list: {
            match: {
                enabled: true
            },
            onChooseEvent: function() {
                $("#breedList").removeClass('wrongBreed');
                var value = $("#breedList").getSelectedItemData().id;
                $("[name=breedId]").val(value);
                if (!value) {
                    $("#breedList").addClass('wrongBreed');
                }
            }
        },
        theme: "square"
    };
    $("#breedList").easyAutocomplete(options);

    $("#breedList").blur(function() {
        var value = $("#breedList").getSelectedItemData().id;
        if (!value) {
            $("#breedList").addClass('wrongBreed');
        }
    });

    $('#main_search').submit(function(e) {
        if ($("#breedList").hasClass('wrongBreed')) {
            e.preventDefault();
            e.stopPropagation();
        }
    });

    $('#orderSelect').change(function() {
        var vals = $(this).val().split(',');
        $('[name=order]').val(vals[0]);
        $('[name=property]').val(vals[1]);
        $('#main_search').submit();
    });

    $('#orderSelect').val($('[name=order]').val() + ',' +$('[name=property]').val());

    $('#applyFilter').click(function() {
        $('[name=minPrice]').val($('#minPrice').val());
        $('[name=maxPrice]').val($('#maxPrice').val());
        //$('[name=gender]').val($('#gender').val());
       // $('[name=readyToCopulation]').val($('#readyToCopulation').prop('checked'));
        $('#main_search').submit();
    });

    //$('#readyToCopulation').checked($('[name=readyToCopulation]').val()==='true');
    //$('#gender').val($('[name=gender]').val());
});


// $( document ).ready(function() {
//
//     // main page search
//     $("#main_search").submit(function( event ) {
//         event.preventDefault();
//         let data = event.currentTarget;
//         let dataForSend = '';
//         let URL = window.location.href;
//         for( let i = 0; i < data.length; i++){
//             if(data[i].name && data[i].value && $.trim(data[i].value).length){
//                 if(data[i].name === 'select'){
//                     if(data[i].value === 'adds') {
//                         if(document.getElementById('searchName').value) {
//                             dataForSend  += 'title',
//                                 dataForSend += '=';
//                             dataForSend += document.getElementById('searchName').value;
//                             dataForSend += '&';
//                             URL += 'search/adverts';
//                         }
//                     } else if (data[i].value === 'animals') {
//                         dataForSend  += 'name',
//                         dataForSend += '=';
//                         dataForSend += document.getElementById('searchName').value;
//                         dataForSend += '&';
//                         URL += 'search/animals';
//                     }
//                 } else if (data[i].name === 'breedId'){
//                     dataForSend  += 'breedId',
//                     dataForSend += '=';
//                     let breedId = changeNameToId(data[i].value);
//                     if(!breedId){
//                         document.getElementById('breedList').classList.add("wrongBreed");
//                         return;
//                     } else {
//                         dataForSend += breedId;
//                         dataForSend += '&';
//                     }
//
//                 }
//                 else {
//                     dataForSend += data[i].name;
//                     dataForSend += '=';
//                     dataForSend += data[i].value;
//                     dataForSend += '&';
//                 }
//             }
//         }
//         if(dataForSend) {
//             let res = dataForSend.substr(0, dataForSend.length - 1);
//             let a = 'search=';
//             res = encodeURIComponent(res);
//             res = a + res;
//             $.get(URL, res);
//         }
//     });
//
//     function changeNameToId(name) {
//         let a = false;
//         breeds.forEach( el =>{
//             if(el.name === name){
//                 a = el.id.toString();
//             }
//         })
//         return a;
//     }
//
//     $.get("http://aristo-pets.com/api/breeds", onAjaxSuccess);
//     let breeds;
//
//     function onAjaxSuccess(data) {
//         breeds = data;
//     }
//
//     // document.getElementById('breedList').addEventListener('keydown', function(e) {
//     //    console.log(e)
//     // }, false)
//
//     var options = {
//         url: "http://aristo-pets.com/api/breeds",
//         getValue: "name",
//         list: {
//             match: {
//                 enabled: true
//             }
//         },
//
//         theme: "square"
//     };
//
//     var parentOpt = {
//         url: "http://aristo-pets.com/api/parents/",
//         getValue: "name",
//         list: {
//             match: {
//                 enabled: true
//             }
//         },
//
//         theme: "square"
//     };
//
//      //$("#breedList").easyAutocomplete(options);
//     $("#breedList").easyAutocomplete(options);
//     $("#parentList").easyAutocomplete(parentOpt);
//
//
//      //adeverts page search  main_
//
//     $("#main_searchAdvert, #filterSend").submit(function( event ) {
//         event.preventDefault();
//         let data = '';
//         let mainField = document.getElementsByClassName('main_searchInputs');
//         if(mainField.length) {
//             let fieldData = '';
//             for(let i = 0; i < mainField.length; i++){
//                 if(mainField[i].value) {
//                      if(mainField[i].name === 'breedId') {
//                          let id = changeNameToId(mainField[i].value);
//                          if(id) {
//                              fieldData +='breedId=' + id + '&';
//                          }
//                      } else {
//                          fieldData += mainField[i].name + '=' + mainField[i].value + '&';
//                      }
//                 }
//             }
//             if (fieldData) {
//                 let res = fieldData.substr(0, fieldData.length - 1);
//                 data += 'search=' + encodeURIComponent(res);
//             }
//         }
//         let orderBy = $('.orderBy_button :selected').val();
//         if(orderBy) {
//             if(data) {data += "&" }
//             data += 'property='  + encodeURIComponent('averagePrice&order=' + orderBy);
//         }
//        let filters =$('.filter_input');
//         let price = ''
//         for( let i = 0; i <filters.length; i++ ) {
//             if(filters[i].value && (filters[i].name === 'min')) {
//                 if(data) {data += "&" }
//                 price += 'averagePrice>' + filters[i].value
//             }
//             if(filters[i].value && (filters[i].name === 'max')) {
//                 if(price) {price += "&" }
//                 price += 'averagePrice<' + filters[i].value
//             }
//         }
//         if(price) {
//             if(data) {data += "&" };
//             data += 'filters=' + encodeURIComponent(price);
//         }
//         let URL = window.location.origin + '/search/adverts/';
//         $.get(URL, data);
//     });
//
//     $('.orderBy_button').change(function( event ) {
//         event.preventDefault();
//         let data = '';
//         let mainField = document.getElementsByClassName('main_searchInputs');
//         if(mainField.length) {
//             let fieldData = '';
//             for(let i = 0; i < mainField.length; i++){
//                 if(mainField[i].value) {
//                     if(mainField[i].name === 'breedId') {
//                         let id = changeNameToId(mainField[i].value);
//                         if(id) {
//                             fieldData +='breedId=' + id + '&';
//                         }
//                     } else {
//                         fieldData += mainField[i].name + '=' + mainField[i].value + '&';
//                     }
//                 }
//             }
//             if (fieldData) {
//                 let res = fieldData.substr(0, fieldData.length - 1);
//                 data += 'search=' + encodeURIComponent(res);
//             }
//         }
//         let orderBy = $('.orderBy_button :selected').val();
//         if(orderBy) {
//             if(data) {data += "&" }
//             data += 'property='  + encodeURIComponent('averagePrice&order=' + orderBy);
//         }
//         let filters =$('.filter_input');
//         let price = ''
//         for( let i = 0; i <filters.length; i++ ) {
//             if(filters[i].value && (filters[i].name === 'min')) {
//                 if(data) {data += "&" }
//                 price += 'averagePrice>' + filters[i].value
//             }
//             if(filters[i].value && (filters[i].name === 'max')) {
//                 if(price) {price += "&" }
//                 price += 'averagePrice<' + filters[i].value
//             }
//         }
//         if(price) {
//             if(data) {data += "&" };
//             data += 'filters=' + encodeURIComponent(price);
//         }
//         let URL = window.location.origin + '/search/adverts/';
//         $.get(URL, data);
//     });
//
//
//
//     //animalsFilters
//     $('.filterprice_button, .main_searchButton').click(function(e) {
//         e.preventDefault();
//         let data = '';
//         let mainField = document.getElementsByClassName('main_searchInputs');
//         if(mainField.length) {
//             let fieldData = '';
//             for(let i = 0; i < mainField.length; i++){
//                 if(mainField[i].value) {
//                     if(mainField[i].name === 'breedId') {
//                         let id = changeNameToId(mainField[i].value);
//                         if(id) {
//                             fieldData +='breedId=' + id + '&';
//                         }
//                     } else {
//                         fieldData += mainField[i].name + '=' + mainField[i].value + '&';
//                     }
//                 }
//             }
//             if (fieldData) {
//                 let res = fieldData.substr(0, fieldData.length - 1);
//                 data += 'search=' + encodeURIComponent(res);
//             }
//         }
//         let orderBy = $('.orderBy_buttonAnimals :selected').val();
//         if(orderBy) {
//             if(data) {data += "&" }
//             data += 'property='  + encodeURIComponent('lookForSomething=' + orderBy);
//         }
//         let filters = $('.animalilter');
//         let filterData = '';
//         if(filters.length){
//             for(let i = 0; i <filters.length; i++ ) {
//                 if(filters[i].name === 'selectGender') {
//                     let orderByMale = $('#maleFilter :selected').val();
//                     if(orderByMale) {
//                         if(filterData) {filterData += "&" }
//                         filterData += 'gender=' + orderByMale;
//                     }
//                 } else {
//                     if(filters[i].checked) {
//                         if(filterData) {filterData += "&" }
//                         filterData += filters[i].name + '=true';
//                     }
//                 }
//             }
//
//             if(filterData) {
//                 if(data) {data += "&" }
//                 data += 'filters=' + encodeURIComponent(filterData);
//             }
//         }
//         if(data){
//             let URL = window.location.origin + '/search/animals/';
//             $.get(URL, data);
//         }
//     })
//
//     $('.orderBy_buttonAnimals').change(function(e) {
//         e.preventDefault();
//         let data = '';
//         let mainField = document.getElementsByClassName('main_searchInputs');
//         if(mainField.length) {
//             let fieldData = '';
//             for(let i = 0; i < mainField.length; i++){
//                 if(mainField[i].value) {
//                     if(mainField[i].name === 'breedId') {
//                         let id = changeNameToId(mainField[i].value);
//                         if(id) {
//                             fieldData +='breedId=' + id + '&';
//                         }
//                     } else {
//                         fieldData += mainField[i].name + '=' + mainField[i].value + '&';
//                     }
//                 }
//             }
//             if (fieldData) {
//                 let res = fieldData.substr(0, fieldData.length - 1);
//                 data += 'search=' + encodeURIComponent(res);
//             }
//         }
//         let orderBy = $('.orderBy_buttonAnimals :selected').val();
//         if(orderBy) {
//             if(data) {data += "&" }
//             data += 'property='  + encodeURIComponent('lookForSomething=' + orderBy);
//         }
//         let filters = $('.animalilter');
//         let filterData = '';
//         if(filters.length){
//             for(let i = 0; i <filters.length; i++ ) {
//                 if(filters[i].name === 'selectGender') {
//                     let orderByMale = $('#maleFilter :selected').val();
//                     if(orderByMale) {
//                         if(filterData) {filterData += "&" }
//                         filterData += 'gender=' + orderByMale;
//                     }
//                 } else {
//                     if(filters[i].checked) {
//                         if(filterData) {filterData += "&" }
//                         filterData += filters[i].name + '=true';
//                     }
//                 }
//             }
//
//             if(filterData) {
//                 if(data) {data += "&" }
//                 data += 'filters=' + encodeURIComponent(filterData);
//             }
//         }
//         if(data){
//             let URL =  window.location.origin  + '/search/animals/';
//             $.get(URL, data);
//         }
//     })
//
// });