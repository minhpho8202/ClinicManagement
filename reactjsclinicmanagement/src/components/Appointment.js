import React, { useContext, useState } from 'react';
import { Form, Container, Row, Col, Button } from 'react-bootstrap';
import Apis, { authApi, endpoints } from '../configs/Apis';
import { MyUserContext } from '../App';
import { Link } from 'react-router-dom';

const Appointment = () => {
    const [user, dispatch] = useContext(MyUserContext);
    const [selectedDate, setSelectedDate] = useState(new Date());
    const now = new Date();

    const handleDateChange = (event) => {
        const newDate = new Date(event.target.value);
        setSelectedDate(newDate);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        
        const createdDate = selectedDate.toISOString();

        if (selectedDate < now) {
            alert("invalid date");
        } else {
            try {
                const response = await authApi().post(endpoints['appointments'], {
                    createdDate: createdDate,
                    patientId: user
                });
                if (response.status === 200) {
                    console.log('successfully');
                    setSelectedDate(new Date());
                    alert("successfully");
                } else {
                    console.log(response.status);
                    alert("fully booked today");
                }
            } catch (error) {
                console.error('failed', error);
            }
        }
    };

    return (
        <Container>
            <h1 className='text text-center text-success'>Book appoiment</h1>
            <Row>
                <Col md={4}>
                    {
                        user === null ?
                            <>
                                <h1>Please login to book an appoiment</h1>
                                <Link className="nav-link text-danger" to="/login">Login right here</Link>
                            </> :
                            <>
                                <Form onSubmit={handleSubmit}>
                                    <Form.Group controlId="createdDate">
                                        <Form.Label>Select time</Form.Label>
                                        <Form.Control
                                            type="datetime-local"
                                            name="createdDate"
                                            value={selectedDate.toISOString().slice(0, 16)}
                                            onChange={handleDateChange}
                                        />
                                    </Form.Group>
                                    <Button variant="primary" type="submit" className='mt-1'>
                                        Submit
                                    </Button>
                                </Form>
                            </>
                    }
                </Col>
            </Row>
        </Container>
    );
};

export default Appointment;
