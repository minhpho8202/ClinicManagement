import { useEffect, useState } from "react";
import { Button, Container, Table } from "react-bootstrap";
import { authApi, endpoints } from "../configs/Apis";
import { Link } from "react-router-dom";

const Shift = () => {
    const [shifts, setShifts] = useState([]);

    const loadShifts = async () => {
        try {
            let res = await authApi().get(endpoints['shifts']);
            setShifts(res.data);
            console.log("successfully", res.data);
        } catch (error) {
            console.error("falied", error);
        }
    }

    useEffect(() => {
        loadShifts();
    }, []);

    function formatDate(dateTimeString) {
        const options = { year: 'numeric', month: 'numeric', day: 'numeric' };
        return new Date(dateTimeString).toLocaleDateString(undefined, options);
    }

    return (
        <Container>
            <h1 className='text text-center text-info'>My shift</h1>
            <Table striped bordered hover>
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Start time</th>
                        <th>End time</th>
                        <th>Description</th>
                    </tr>
                </thead>
                <tbody>
                    {shifts && shifts.map((shift) => (
                        <tr key={shift.id}>
                            <td>{shift.shiftId.name}</td>
                            <td>{formatDate(shift.shiftId.startTime)}</td>
                            <td>{formatDate(shift.shiftId.endTime)}</td>
                            <td>{shift.shiftId.description}</td>
                        </tr>
                    ))}
                </tbody>

            </Table>
        </Container>
    )
}

export default Shift;