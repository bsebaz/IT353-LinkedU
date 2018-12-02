/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var checked = 0;
$(document).ready(function () {
    $('input.compare:checkbox').prop('checked', false);
    $("input.compare-btn").attr("disabled", true);
    $("input.compare").click(increment);
});

function increment() {
    if (this.checked) {
        checked++;
        if (checked === 2) {
            $("input.compare:checkbox:not(:checked)").attr("disabled", true);
            $("input.compare-btn").removeAttr("disabled"); 
        }
    } else {
        checked--;
        if (checked < 2) {
            $("input.compare:checkbox:not(:checked)").removeAttr("disabled");
            $("input.compare-btn").attr("disabled", true);
        }
    }
}
