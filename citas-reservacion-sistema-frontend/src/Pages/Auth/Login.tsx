import { Button, Box, Group, TextInput } from '@mantine/core';
import { useForm } from '@mantine/form';
import { baseUrl } from '../../Utils/baseUrl.tsx'
import { useState } from 'react';
import './Style.css'
export default function Login () {

    const [loading, setLoading] = useState(false);

    const form = useForm({
    mode: 'uncontrolled',
    initialValues: {
        dni: '',
        password: ''
    },

    
        });

    const handleSubmit = async (values: typeof form.values) => {
        setLoading(true);

        const resp = await fetch (baseUrl + 'auth/login', {
            method: 'POST',
            body: JSON.stringify(values),
            headers: {
                'Content-Type': 'application/json',
                accept: 'application/json'
        }
    })

    const data = await resp.json();
    if (data.sucess) {
        console.log(data)
        localStorage.setItem('token', data.data.token);
        localStorage.setItem('user', JSON.stringify(data.data.user));
        window.location.reload();
    } else {
        alert(data.message)
    }
    setLoading(false)
    }

    return(
        <div className='tw-component-form'>
        <Box display="flex" style={{justifyContent: 'Center', alignItems: 'Center'}}>
        <form onSubmit={form.onSubmit((values) => handleSubmit(values))}>
            <text>DNI:</text>
            <TextInput
            withAsterisk
            placeholder="Ingrese su correo "
            key={form.key('dni')}
            {...form.getInputProps('dni')}
            />
            <text>Conntraseña:</text>
            <TextInput
            withAsterisk
            placeholder="Ingrese su contraseña "
            key={form.key('password')}
            {...form.getInputProps('password')}
            />

            <Group justify="flex-end" mt="md">
            <Button loading={loading} type="submit">Ingresar</Button>
            </Group>
        </form>
        </Box>
        </div>
    )
}