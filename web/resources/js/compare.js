/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var checked = 0;
$(document).ready(function () {
    checked = $("input.compare:checkbox:checked").length;
    if (checked === 2) {
        $("input.compare:checkbox:not(:checked)").attr("disabled", true);
        $(".compare-btn").removeAttr("disabled");
    }
    else {
        $(".compare-btn").attr("disabled", true);
    }
    $(".compare").click(increment);
});

function increment() {
    if (this.checked) {
        checked++;
        if (checked === 2) {
            $("input.compare:checkbox:not(:checked)").attr("disabled", true);
            $(".compare-btn").removeAttr("disabled"); 
        }
    } else {
        checked--;
        if (checked < 2) {
            $("input.compare:checkbox:not(:checked)").removeAttr("disabled");
            $(".compare-btn").attr("disabled", true);
        }
    }
}
