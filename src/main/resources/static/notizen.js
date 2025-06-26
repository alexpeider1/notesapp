function ladeNotizen() {
    fetch ('/api/notes') //Request an Backend (GET /api/notes)
    .then(response => response.json()) //Response wird in JSON umgewandelt, dass es JS handeln kann
    .then(notizen => { //Daten weiterverarbeiten
        const container = document.getElementById('notizen-container'); //HTML Element holen
        container.innerHTML = ''; //Content leeren

        for (let n of notizen) { //Jede Notiz aus Array durchgehen
            //für jede Notiz HTML generieren und einfügen
            container.innerHTML += `
                <div class="notiz">
                    <h3>${n.title}</h3>
                    <p>${n.content}</p>
                    <button onclick="bearbeiten(${n.id}, '${n.title}', '${n.content}')">Bearbeiten</button> <!-- Button für bearbeiten -->
                    <button onclick="loeschen(${n.id})">Löschen</button> <!-- Button für löschen -->
                </div>`;
        }
    });
}

function addNotizen(){
    const title = document.getElementById('title'); //Titel aus HTML lesen
    const content = document.getElementById('content'); //content aus HTML lesen

    //wenn ein Feld leer, alert anzeigen
    if (!title || !content) {
        alert("Titel und Inhalt eingeben!")
        return;
    }

    fetch ('/api/notes', { //Request an Backend
        method: 'POST', //Speichern mit POST
        headers: {'Content-Type': 'application/json'}, //JSON wird gesendet
        //Daten in JSON String umwandeln
        body: JSON.stringify({
            title: title.value,
            content: content.value
        })
    }).then(() => {
        //HTML nach Speichern leeren
        title.value = '';
        content.value = '';

        ladeNotizen(); //Notizen neu laden
    });
}

function loeschen(id) {
    fetch('/api/notes/' + id, { method: 'DELETE' }) //HTTP DELETE request an Backend mit ID
        .then(() => ladeNotizen());
}

function bearbeiten(id, title, content) {
    const neuerTitle = prompt("Neuer Titel", title);
    const neuerContent = prompt("Neuer Titel", content);

    if (neuerTitle && neuerContent) {
        fetch('/api/notes/' + id, { //PUT request an Backend mit ID
            method: 'PUT', //PUT zum updaten
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({title: neuerTitle, content: neuerContent}) //neue Daten
        })
            .then(() => ladeNotizen());
    }
}

window.onload = ladeNotizen; //Notizen neu laden bei refresh