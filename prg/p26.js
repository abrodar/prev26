function getTopLevelTable(el) {
    let current = el;
    let topTable = null;
    while (current) {
        if (current.tagName === 'TABLE') {
            topTable = current;
        }
        current = current.parentElement;
    }
    return topTable;
}
var script = document.createElement('script');
function render () {
    let elements = Array.from(document.querySelectorAll("*")).filter(el => el.textContent === "defAt: ").forEach((element) => {
        let tlt = getTopLevelTable(element);
        let label = element.parentElement.parentElement.children[0].children[1].innerText.split(" ")[0]
        let target = Array.from(tlt.querySelectorAll("*")).filter(el => el.textContent === label + ":")[0];
        let colors = ["red", "orange", "blue", "black", "green", "lightblue", "pink", "magenta", "purple", "yellow"];
        new LeaderLine(element, target, {color: colors[Math.floor(Math.random()*colors.length)]});
    });
}
script.onload = function () {
    render();
};
script.src = "https://splet.sijanec.eu/dir/leader-line.min.js";
if (document.currentScript.dataset.deferarrows == undefined)
    document.head.appendChild(script);
else {
    document.addEventListener("keypress", function(event) {
        if (event.keyCode == 13) {
            document.head.appendChild(script);
        }
    });
}