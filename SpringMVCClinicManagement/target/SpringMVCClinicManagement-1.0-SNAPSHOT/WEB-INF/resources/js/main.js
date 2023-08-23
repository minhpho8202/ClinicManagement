function deleteUser(path, id) {
    Swal.fire({
        title: "Confrim",
        text: "Are you sure to delete this user ?",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Delete",
        cancelButtonText: "Cancel"
    }).then(result => {
        if (result.isConfirmed) {
            fetch(path, {
                method: "delete"
            }).then(res => {
                if (res.status === 204) {
                    location.reload();
                } else {
                    Swal.fire("Error", "Something went wrong!", "error");
                }
            });
        }
    });
}

function deleteMedicine(path, id) {
    Swal.fire({
        title: "Confrim",
        text: "Are you sure to delete this medicine ?",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Delete",
        cancelButtonText: "Cancel"
    }).then(result => {
        if (result.isConfirmed) {
            fetch(path, {
                method: "delete"
            }).then(res => {
                if (res.status === 204) {
                    location.reload();
                } else {
                    Swal.fire("Error", "Something went wrong!", "error");
                }
            });
        }
    });
}

function deleteShift(path, id) {
    Swal.fire({
        title: "Confrim",
        text: "Are you sure to delete this shift ?",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Delete",
        cancelButtonText: "Cancel"
    }).then(result => {
        if (result.isConfirmed) {
            fetch(path, {
                method: "delete"
            }).then(res => {
                if (res.status === 204) {
                    location.reload();
                } else {
                    Swal.fire("Error", "Something went wrong!", "error");
                }
            });
        }
    });
}

function deleteUserShift(path, id) {
    Swal.fire({
        title: "Confrim",
        text: "Are you sure to remove this employee out this shift ?",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Delete",
        cancelButtonText: "Cancel"
    }).then(result => {
        if (result.isConfirmed) {
            fetch(path, {
                method: "delete"
            }).then(res => {
                if (res.status === 204) {
                    location.reload();
                } else {
                    Swal.fire("Error", "Something went wrong!", "error");
                }
            });
        }
    });
}
