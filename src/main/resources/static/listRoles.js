const inputRolesNew = document.querySelector('#inputRoles-new')

function addRolesForSelect(role, iR){
    let optionRole = document.createElement('option')
    optionRole.text = role.roleName
    optionRole.value = role.id
    iR.appendChild(optionRole)
}

const urlRoles = 'http://localhost:8080/api/roles'

fetch(urlRoles)
    .then(res => res.json())
    .then(data => {
        data.forEach(role => {
            addRolesForSelect(role, inputRolesNew)
        })
    })