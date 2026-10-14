<template>
  <main class="page">
    <header class="hero">
      <div>
        <p class="eyebrow">STUDENT PORTAL</p>
        <h1>Course Registration</h1>
        <p class="subtitle">Register for a course and manage your registrations.</p>
      </div>
      <div class="count-card">
        <span>Total registrations</span>
        <strong>{{ registrations.length }}</strong>
      </div>
    </header>

    <section class="panel">
      <h2>{{ editingId ? 'Update Registration' : 'Register for a Course' }}</h2>

      <form class="form-grid" @submit.prevent="saveRegistration">
        <label>
          Student name
          <input v-model.trim="form.studentName" required placeholder="Enter full name" />
        </label>

        <label>
          Register number
          <input v-model.trim="form.registerNumber" required placeholder="e.g. 3123..." />
        </label>

        <label>
          Email
          <input v-model.trim="form.email" type="email" required placeholder="student@example.com" />
        </label>

        <label>
          Department
          <select v-model="form.department" required>
            <option disabled value="">Select department</option>
            <option>CSE</option>
            <option>IT</option>
            <option>ECE</option>
            <option>EEE</option>
            <option>Mechanical</option>
            <option>Biomedical</option>
          </select>
        </label>

        <label class="full-width">
          Course
          <select v-model="form.courseName" required>
            <option disabled value="">Select a course</option>
            <option>Web Application Development</option>
            <option>Machine Learning</option>
            <option>Computer Networks</option>
            <option>Database Management Systems</option>
            <option>Cyber Security</option>
          </select>
        </label>

        <div class="actions full-width">
          <button type="submit" :disabled="saving">
            {{ saving ? 'Saving...' : editingId ? 'Update Registration' : 'Register' }}
          </button>
          <button v-if="editingId" type="button" class="secondary" @click="resetForm">
            Cancel
          </button>
        </div>
      </form>

      <p v-if="message" class="message" :class="{ error: isError }">{{ message }}</p>
    </section>

    <section class="panel">
      <div class="list-heading">
        <div>
          <h2>Registrations</h2>
          <p class="muted">Records saved in MongoDB</p>
        </div>
        <input v-model="search" class="search" placeholder="Search name or course..." />
      </div>

      <p v-if="loading" class="muted">Loading registrations...</p>
      <p v-else-if="filteredRegistrations.length === 0" class="muted">
        No registrations found.
      </p>

      <div v-else class="table-wrap">
        <table>
          <thead>
            <tr>
              <th>Student</th>
              <th>Register No.</th>
              <th>Department</th>
              <th>Course</th>
              <th>Email</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="item in filteredRegistrations" :key="item.id">
              <td>{{ item.studentName }}</td>
              <td>{{ item.registerNumber }}</td>
              <td>{{ item.department }}</td>
              <td>{{ item.courseName }}</td>
              <td>{{ item.email }}</td>
              <td class="row-actions">
                <button class="small secondary" @click="editRegistration(item)">Edit</button>
                <button class="small danger" @click="deleteRegistration(item.id)">Delete</button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </section>
  </main>
</template>

<script setup>
import { computed, onMounted, reactive, ref } from 'vue'

const API_URL = 'http://localhost:8080/api/courses'

const registrations = ref([])
const loading = ref(false)
const saving = ref(false)
const search = ref('')
const message = ref('')
const isError = ref(false)
const editingId = ref(null)

const emptyForm = () => ({
  studentName: '',
  registerNumber: '',
  email: '',
  department: '',
  courseName: ''
})

const form = reactive(emptyForm())

const filteredRegistrations = computed(() => {
  const query = search.value.toLowerCase()
  return registrations.value.filter(item =>
    (item.studentName || '').toLowerCase().includes(query) ||
    (item.courseName || '').toLowerCase().includes(query) ||
    (item.registerNumber || '').toLowerCase().includes(query)
  )
})

function setMessage(text, error = false) {
  message.value = text
  isError.value = error
}

async function loadRegistrations() {
  loading.value = true
  try {
    const response = await fetch(API_URL)
    if (!response.ok) throw new Error('Could not load registrations')
    registrations.value = await response.json()
  } catch (error) {
    setMessage('Cannot connect to backend. Start MongoDB and Spring Boot first.', true)
  } finally {
    loading.value = false
  }
}

async function saveRegistration() {
  saving.value = true
  setMessage('')
  try {
    const isEditing = Boolean(editingId.value)
    const url = isEditing ? `${API_URL}/${editingId.value}` : API_URL
    const response = await fetch(url, {
      method: isEditing ? 'PUT' : 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ ...form })
    })

    if (!response.ok) throw new Error('Save failed')

    setMessage(isEditing ? 'Registration updated successfully.' : 'Registration created successfully.')
    resetForm()
    await loadRegistrations()
  } catch (error) {
    setMessage('Could not save. Check that Spring Boot and MongoDB are running.', true)
  } finally {
    saving.value = false
  }
}

function editRegistration(item) {
  editingId.value = item.id
  Object.assign(form, {
    studentName: item.studentName || '',
    registerNumber: item.registerNumber || '',
    email: item.email || '',
    department: item.department || '',
    courseName: item.courseName || ''
  })
  setMessage('Editing registration. Update the fields and save.')
  window.scrollTo({ top: 0, behavior: 'smooth' })
}

function resetForm() {
  editingId.value = null
  Object.assign(form, emptyForm())
}

async function deleteRegistration(id) {
  if (!window.confirm('Delete this registration?')) return

  setMessage('')
  try {
    const response = await fetch(`${API_URL}/${id}`, { method: 'DELETE' })
    if (!response.ok) throw new Error('Delete failed')
    setMessage('Registration deleted successfully.')
    if (editingId.value === id) resetForm()
    await loadRegistrations()
  } catch (error) {
    setMessage('Could not delete registration. Check the backend connection.', true)
  }
}

onMounted(loadRegistrations)
</script>