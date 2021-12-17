function sortTable(tableid, collumn){
    console.log(tableid)
    var table, rows, switching, i, x, y, shouldSwitch, switchcount = 0;
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
            console.log(x)
            console.log(y)
        /*    if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
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
                switching = true;
            }
        }*/
    }}
}