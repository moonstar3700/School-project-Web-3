function sortTableNumber(tableid, collumn){
    console.log(tableid)
    var table, rows, switching, i, x, y, shouldSwitch, switchcount = 0;
    table = document.getElementById(tableid);
    switching = true;
    shouldSwitch = false;
    while (switching) {
        switching = false;
        rows = table.rows;
        console.log(rows)
        for (i = 1; i < (rows.length - 1); i++){
            shouldSwitch = false;
            x = rows[i].getElementsByTagName("TD")[collumn];
            y = rows[i + 1].getElementsByTagName("TD")[collumn];
            console.log(x)
            console.log(y)
            if (Number(x.innerHTML) > Number(y.innerHTML)) {
                shouldSwitch = true;
                break;
            }
        }
        if (shouldSwitch) {
            rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
            switching = true;
            switchcount ++;
        } else {
            if (switchcount == 0) {
                switching = false;
            }
        }
    }
}

function sortTableDate(tableid, collumn){
    console.log(tableid)
    var table, rows, switching, i, x, y, shouldSwitch, switchcount = 0, d1, d2;
    table = document.getElementById(tableid);
    switching = true;
    while (switching) {
        switching = false;
        rows = table.rows;
        console.log(rows)
        for (i = 1; i < (rows.length - 1); i++){
            shouldSwitch = false;
            x = rows[i].getElementsByTagName("TD")[collumn];
            y = rows[i + 1].getElementsByTagName("TD")[collumn];
            d1 = new Date(x.innerHTML);
            d2 = new Date(y.innerHTML);
            console.log(x)
            console.log(y)
            if (d1 > d2) {
                shouldSwitch = true;
                break;
            }
        }
        if (shouldSwitch) {
            rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
            switching = true;
            switchcount ++;
        } else {
            if (switchcount == 0) {
                switching = false;
            }
        }
    }
}

function sortTableTime(tableid, collumn){
    console.log(tableid)
    var table, rows, switching, i, x, y, shouldSwitch, switchcount = 0, d1, d2;
    table = document.getElementById(tableid);
    switching = true;
    while (switching) {
        switching = false;
        rows = table.rows;
        console.log(rows)
        for (i = 1; i < (rows.length - 1); i++){
            shouldSwitch = false;
            x = rows[i].getElementsByTagName("TD")[collumn];
            y = rows[i + 1].getElementsByTagName("TD")[collumn];
            var date1 = x.innerHTML.split(':')
            var date2 = y.innerHTML.split(':')
            var tot1 = date1[0] * 60 + date1[1]
            var tot2 = date2[0] * 60 + date2[1]
            if (tot1 > tot2) {
                shouldSwitch = true;
                break;
            }
        }
        if (shouldSwitch) {
            rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
            switching = true;
            switchcount ++;
        } else {
            if (switchcount == 0) {
                switching = false;
            }
        }
    }
}