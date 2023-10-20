import { useEffect, useRef, useState } from "react";
import { Alert, Button, FloatingLabel, Form } from "react-bootstrap";
import Apis, { endpoints } from "../configs/Apis";
import { useNavigate } from "react-router-dom";
import MySpinner from "../layout/MySpinner";

const Register = () => {
    const [user, setUser] = useState({
        "firstName": "",
        "lastName": "",
        "gender": "Male",
        "email": "",
        "phoneNumber": "",
        "address": "",
        "username": "",
        "password": "",
        "confirmPassword": ""
    });

    const [selectedDate, setSelectedDate] = useState(new Date());

    const avatar = useRef();

    const nav = useNavigate();

    const [err, setErr] = useState(null);

    const [loading, setLoading] = useState(false);

    const [usernameState, setUsernameState] = useState(false);
    const [emailState, setEmailState] = useState(false);

    const register = (evt) => {
        evt.preventDefault();

        const dateOfBirth = selectedDate.toISOString();

        const process = async () => {
            let form = new FormData();

            for (let field in user)
                form.append(field, user[field])

            if (avatar.current.files.length > 0) {
                form.append("avatar", avatar.current.files[0]);
            }
            form.append("dateOfBirth", dateOfBirth);
            setLoading(true);
            for (let pair of form.entries()) {
                console.log(pair[0] + ': ' + pair[1]);
            }
            let res = await Apis.post(endpoints['register'], form);
            if (res.status === 201)
                nav("/login");
            else
                setErr("something went wrong");
        };

        if (user.password === user.confirmPassword)
            if (emailState === true)
                if (usernameState === true)
                    process();
                else
                    setErr("please check username");
            else
                setErr("please check email");
        else
            setErr("password does not match");
    };

    const change = (evt, field) => {
        // setUser({...user, [field]: evt.target.value});
        setUser(current => {
            return { ...current, [field]: evt.target.value }
        })
    };

    const handleDateChange = (event) => {
        const newDate = new Date(event.target.value);
        setSelectedDate(newDate);
    };

    const checkUsername = async (username) => {
        try {
            let e = endpoints['check-username'];
            e = `${e}?username=${username}`;
            const res = await Apis.get(e);
            console.log(res.data)
            console.log(username)
            if (res.data === true) {
                setUsernameState(true);
                alert("valid username")
            }
            else {
                alert("invalid username")
            }
            return res.status;
        } catch (error) {
            console.error(error);
            return false;
        }
    };

    const checkEmail = async (email) => {
        try {
            let e = endpoints['check-email'];
            e = `${e}?email=${email}`;
            const res = await Apis.get(e);
            console.log(res.data);
            console.log(email);
            if (res.data === true) {
                setEmailState(true);
                alert("valid email")
            }
            else {
                alert("invalid email")
            }
            return res.status;
        } catch (error) {
            console.error(error);
            return false;
        }
    };

    useEffect(() => {
        setEmailState(false);
    }, [user.email]);

    useEffect(() => {
        setUsernameState(false);
    }, [user.username]);

    console.log("username" + usernameState);
    console.log("email" + emailState);


    return (
        <>
            <h1 className="text-center text-info mt-2" >Register account</h1>

            {err === null ? "" : <Alert variant="danger" >{err}</Alert>}
            <Form className="container form-control mb-2" onSubmit={register}>
                <FloatingLabel controlId="firstName" label="First name" className="mb-3"
                >
                    <Form.Control value={user.firstName} onChange={e => change(e, "firstName")} type="text" placeholder="..." required />
                </FloatingLabel>
                <FloatingLabel controlId="lastName" label="Last name" className="mb-3">
                    <Form.Control value={user.lastName} onChange={e => change(e, "lastName")} type="text" placeholder="..." required />
                </FloatingLabel>
                <FloatingLabel controlId="dateOfBirth" label="Date of birth" className="mb-3"
                >
                    <Form.Control
                        type="date"
                        name="dateOfBirth"
                        value={selectedDate.toISOString().slice(0, 10)}
                        onChange={handleDateChange}
                    />
                </FloatingLabel>
                <FloatingLabel controlId="gender" label="Gender" className="mb-3">
                    <Form.Select
                        value={user.gender}
                        onChange={e => change(e, "gender")}
                    >
                        <option value="Male">Male</option>
                        <option value="Female">Female</option>
                        <option value="Other">Other</option>
                    </Form.Select>
                </FloatingLabel>
                <FloatingLabel controlId="email" label="Email" className="mb-3"
                >
                    <Form.Control value={user.email} onChange={e => change(e, "email")} type="email" placeholder="..." required />
                </FloatingLabel>
                <Button variant="primary" onClick={() => checkEmail(user.email)} className="mb-3">Check Email</Button>
                <FloatingLabel controlId="phoneNumber" label="Phone number" className="mb-3"
                >
                    <Form.Control value={user.phoneNumber} onChange={e => change(e, "phoneNumber")} type="number" placeholder="..." required />
                </FloatingLabel>
                <FloatingLabel controlId="address" label="Address" className="mb-3"
                >
                    <Form.Control value={user.address} onChange={e => change(e, "address")} type="text" placeholder="..." required />
                </FloatingLabel>
                <FloatingLabel controlId="username" label="Username" className="mb-3"
                >
                    <Form.Control value={user.username} onChange={e => change(e, "username")} type="text" placeholder="..." required />
                </FloatingLabel>
                <Button variant="primary" onClick={() => checkUsername(user.username)} className="mb-3">Check Username</Button>
                <FloatingLabel controlId="password" label="Password" className="mb-3">
                    <Form.Control value={user.password} onChange={e => change(e, "password")} type="password" placeholder="..." required />
                </FloatingLabel>
                <FloatingLabel controlId="confirmPassword" label="Confirm password" className="mb-3">
                    <Form.Control value={user.confirmPassword} onChange={e => change(e, "confirmPassword")} type="password" placeholder="..." required />
                </FloatingLabel>
                <FloatingLabel controlId="file" label="Avatar" className="mb-3"
                >
                    <Form.Control type="file" ref={avatar} className="mb-3" required />
                </FloatingLabel>
                {
                    loading === true ? <MySpinner /> :
                        <Button type="submit" variant="success" className="mt-2" size="lg">Register</Button>
                }
            </Form>
            {err === null ? "" : <Alert variant="danger" >{err}</Alert>}
        </>
    )
}

export default Register;