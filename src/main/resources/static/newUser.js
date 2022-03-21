const postNewUser = document.querySelector('#postNewUser')
const navUsersTab = document. querySelector('#nav-users-tab')
const emailNew = document.querySelector('#email-new')
const passwordNew = document.querySelector('#password-new')
const firstnameNew = document.querySelector('#firstname-new')
const lastnameNew = document.querySelector('#lastname-new')
const ageNew = document.querySelector('#age-new')

const urlNewUser = 'http://localhost:8080/api/users'

postNewUser.addEventListener('submit', (e) => {
    e.preventDefault()

    fetch(urlNewUser + '?inputRoles=' + getSelectValues(inputRolesNew),{
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            'email': emailNew.value,
            'password': passwordNew.value,
            'firstname': firstnameNew.value,
            'lastname': lastnameNew.value,
            'age': ageNew.value
        })
    })
        .then(res => res.json())
        .then(res => {
            console.log(res)
            navUsersTab.click()
            showUser(res, tableBodyAdmin)
            emailNew.value = ''
            passwordNew.value = ''
            firstnameNew.value = ''
            lastnameNew.value = ''
            ageNew.value = ''
        })
})

function getSelectValues(select) {
    var result = [];
    var options = select && select.options;
    var opt;

    for (var i=0, iLen=options.length; i<iLen; i++) {
        opt = options[i];

        if (opt.selected) {
            result.push(opt.value);
        }
    }
    return result;
}