const uri = 'api/CarTypes'; 

let carTypes = []; 
function getCarTypes() {
    fetch(uri) 
        .then(response => {
            if (!response.ok) { 
                return response.text().then(text => {
                    throw new Error(text)
                })
            } 
            document.getElementById('errorDB').innerHTML = "";
            return response.json();
        }) 
        .then(data => _displayCarTypes(data))
        .catch(error => document.getElementById('errorDB').innerHTML =
            error.toString());
}
function addCarType() {

    const addTypeNameTextbox = document.getElementById('add-typeName');

    const carType = {
        typeName: addTypeNameTextbox.value.trim(),
    };

    fetch(uri, {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(carType)
    })
        .then(response => {
            if (!response.ok) { 
                return response.text().then(text => {
                    throw new Error(text)
                })
            } 
            document.getElementById('errorDB').innerHTML = "";
            return response.json();
        }) 
        .then(() => {
            getCarTypes(); 
            addTypeNameTextbox.value = ''; 
        })
        .catch(error => document.getElementById('errorDB').innerHTML =
            error.toString());
}
function deleteCarType(id) {
    fetch(`${uri}/${id}`, {
        method: 'DELETE'
    })
        .then(() => getCarTypes())
        .catch(error => document.getElementById('errorDB').innerHTML =
            error.toString());
}
function displayEditForm(id) {
    const carType = carTypes.find(carType => carType.id === id);

    document.getElementById('edit-id').value = carType.id;
    document.getElementById('edit-typeName').value = carType.typeName;
    document.getElementById('editCarType').style.display = 'block';
}

function updateCarType() {

    const carTypeId = document.getElementById('edit-id').value;
    const carType = {
        id: parseInt(carTypeId, 10),
        typeName: document.getElementById('edit-typeName').value.trim(),
}

    fetch(`${uri}/${carTypeId}`, {
        method: 'PUT',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(carType)
    })
        .then(response => {
            if (!response.ok) {
                return response.text().then(text => {
                    throw new Error(text)
                })
            }
            document.getElementById('errorDB').innerHTML = "";
            return getCarTypes();
        })
        .then(() => getCarTypes())
        .catch(error => document.getElementById('errorDB').innerHTML =
            error.toString());
    closeInput(); 
    return false;
}
function closeInput() {

    document.getElementById('editCarType').style.display = 'none';
    document.getElementById('errorDB').innerHTML = '';
}
function _displayCarTypes(data) {
    const tBody = document.getElementById('carTypes');
    tBody.innerHTML = '';
    const button = document.createElement('button');
    data.forEach(carType => {
        let editButton = button.cloneNode(false);
        editButton.innerText = 'Редагувати';
        editButton.setAttribute('onclick',
            `displayEditForm(${carType.id})`);
        let deleteButton = button.cloneNode(false);
        deleteButton.innerText = 'Видалити';
        deleteButton.setAttribute('onclick', `deleteCarType(${carType.id})`);
        let tr = tBody.insertRow(); 
    let td0 = tr.insertCell(0); 
    let textNodeId = document.createTextNode(carType.id);
    td0.appendChild(textNodeId);
    let td1 = tr.insertCell(1); 
    let textNodeTypeName = document.createTextNode(carType.typeName);
    td1.appendChild(textNodeTypeName);
    let td2 = tr.insertCell(2); 
    td2.appendChild(editButton);
    let td3 = tr.insertCell(3); 
    td3.appendChild(deleteButton);
});
carTypes = data; 
}