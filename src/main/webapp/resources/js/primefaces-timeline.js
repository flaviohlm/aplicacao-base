function timelineUpdate() {
    var timelineAxisText = document.querySelectorAll('.timeline-axis-text');
    for (var i = 0; i < timelineAxisText.length; i++) {
        timelineBR(timelineAxisText[i]);
        timelineAxisText[i].addEventListener('change', timelineBR);
    }
}
function timelineBR(e) {
    var mes = e.textContent;
    switch(mes){
        case 'Feb':
            e.textContent = 'Fev';
            break;
        case 'Apr':
            e.textContent = 'Abr';
            break;
        case 'May':
            e.textContent = 'Mai';
            break;
        case 'Aug':
            e.textContent = 'Ago';
            break;
        case 'Sep':
            e.textContent = 'Set';
            break;
        case 'Oct':
            e.textContent = 'Out';
            break;
        case 'Dec':
            e.textContent = 'Dez';
            break;
        default:
    }
}

window.onload = timelineUpdate;