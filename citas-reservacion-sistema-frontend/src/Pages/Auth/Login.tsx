import { Button, Box, Group, TextInput } from '@mantine/core';
import { useForm } from '@mantine/form';

export default function Login () {

    const form = useForm({
    mode: 'uncontrolled',
    initialValues: {
        email: '',
        password: ''
    },

    validate: {
        email: (value) => (/^\S+@\S+$/.test(value) ? null : 'Invalid email'),
        password: (value) => value.length < 6 ? 'Invalid Password' :null
    },
        });
    return(
        <Box display="flex" style={{justifyContent: 'Center', alignItems: 'Center'}}>
        <form onSubmit={form.onSubmit((values) => console.log(values))}>
            <text>Correo:</text>
            <TextInput
            withAsterisk
            placeholder="Ingrese su correo "
            key={form.key('email')}
            {...form.getInputProps('email')}
            />
            <text>Conntraseña:</text>
            <TextInput
            withAsterisk
            placeholder="Ingrese su contraseña "
            key={form.key('password')}
            {...form.getInputProps('password')}
            />

            <Group justify="flex-end" mt="md">
            <Button type="submit">Ingresar</Button>
            </Group>
        </form>
        </Box>
    )
}
